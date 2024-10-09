package com.example.sdmw.Views

import androidx.compose.ui.graphics.Color
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sdmw.Repository.Card
import com.example.sdmw.ViewModel.AppViewModel

@Composable
fun BankCardScreen(
    viewModel: AppViewModel,
    onClickBack: () -> Unit
) {
    var showForm by remember { mutableStateOf(false) }
    var selectedCard by remember { mutableStateOf<Card?>(null) }
    val cards = viewModel.cards.value ?: emptyList()
    val colors : List<Color> = listOf(
        Color(0xFFFF9800),
        Color(0xFF3A6D88),
        Color(0xFF324FA3),
        Color(0xFFE9D51E),)
    Scaffold(
        topBar = {
            Topbar(
                onClickBack = { onClickBack() },
                Primarytext = "Bank Cards",
                secondtext = "Click on Add Button to Add Card"
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedCard = null
                showForm = true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
             items(cards) { card ->
                 BankCardUi(
                     modifier = Modifier.padding(16.dp),
                     cardNumber = card.number,
                     expires = card.expiry,
                     cvv = card.cvv,
                     brand = card.brand,
                     baseColor = colors[cards.indexOf(card) % colors.size]
                 )
             }
        }
        if (showForm) {
            BankCardForm(
                card = selectedCard,
                onDismiss = { showForm = false },
                onSave = { viewModel.addCard(it)
                     showForm = false }
            )
        }
    }
}

@Composable
fun BankCardForm(card: Card?, onDismiss: () -> Unit, onSave: (Card) -> Unit) {
    // Local state for the form fields, using card details if editing
    var number by remember { mutableStateOf(card?.number ?: "") }
    var expiry by remember { mutableStateOf(card?.expiry ?: "") }
    var cvv by remember { mutableStateOf(card?.cvv ?: "") }
    var name by remember { mutableStateOf(card?.number ?: "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = if (card == null) "Add Card" else "Edit Card")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Card Name") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = number,
                    onValueChange = { number = it },
                    label = { Text("Card Number") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = expiry,
                    onValueChange = { expiry = it },
                    label = { Text("Expiry Date") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    label = { Text("CVV") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                // Save the data and close the form
                onSave(Card(number = number, expiry = expiry, cvv = cvv, name = name))
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
