package com.example.sdmw.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.ViewModel.AppViewModel
import com.example.sdmw.ui.theme.Color1


@Composable
fun VendorsScreen(
    viewModel: AppViewModel,
    onClickBack: () -> Unit
){

    var showForm by remember { mutableStateOf(false) }
    var selectedVendor by remember { mutableStateOf<VendorDetails?>(null) }

    val vendors = viewModel.vendors.value ?: emptyList()

    Scaffold(
        topBar = {
            Topbar(onClickBack = { onClickBack() },
                Primarytext = "Vendors",
                secondtext = "Click on Add Button to Add Vendor")
        },
        floatingActionButton = { FloatingActionButton(onClick = {
            selectedVendor = null
            showForm = true}
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = null)
        } }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(vendors){
                VendorCard(data = it, bg_color = Color1 ,
                    onEditClick = { vendor ->
                        selectedVendor = vendor
                        showForm = true
                    })
            }
        }
        if (showForm) {
            VendorForm(
                vendor = selectedVendor,
                onDismiss = { showForm = false },
                onSave = { viewModel.addVendor(it)
                    showForm = false }
            )
        }
    }
}


@Composable
fun VendorForm(vendor: VendorDetails?, onDismiss: () -> Unit, onSave: (VendorDetails) -> Unit) {
    // Local state for the form fields, using vendor details if editing
    var name by remember { mutableStateOf(vendor?.name ?: "") }
    var address by remember { mutableStateOf(vendor?.address ?: "") }
    var contact by remember { mutableStateOf(vendor?.contact ?: "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = if (vendor == null) "Add Vendor" else "Edit Vendor")
        },
        text = {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp),
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                // Save the data and close the form
                onSave(VendorDetails(name = name, address = address, contact = contact))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
