package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.ViewModel.AppViewModel


@Composable
fun FestivalApp(
    viewModel: AppViewModel,
    onClickBack: () -> Unit
) {

    Scaffold(
        topBar = {
            Topbar(onClickBack = { onClickBack() },
                Primarytext = "Vendors",
                secondtext = "Click on Add Button to Add Vendor")
        }
    ) {
        MaterialTheme {
            val festivalsByMonth by viewModel.festivalsByMonth.collectAsState()
            FestivalList(festivalsByMonth, modifier = Modifier.padding(it))
        }
    }

}

@Composable
fun FestivalList(festivalsByMonth: List<Pair<String,
        MutableList<Pair<String, String>>>>,
                 modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(festivalsByMonth) { (month, festivals) ->
            MonthCard(
                month = month,
                festivals = festivals,
                onAddFestival = { festival, date ->
                    festivals.add(festival to date)
                }
            )
        }
    }
}

@Composable
fun MonthCard(
    month: String,
    festivals: List<Pair<String, String>>,
    onAddFestival: (String, String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showAddFestival by remember { mutableStateOf(false) }
    var newFestivalName by remember { mutableStateOf("") }
    var newFestivalDate by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Display month name
            Text(
                text = month,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Toggle to show/hide festivals
            if (expanded) {
                Column {
                    festivals.forEach { (festival, date) ->
                        FestivalRow(festival = festival, date = date)
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // Button to toggle festival form
                    if (!showAddFestival) {
                        Button(
                            onClick = { showAddFestival = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Add New Festival")
                        }
                    }

                    // Form to add a new festival
                    if (showAddFestival) {
                        Spacer(modifier = Modifier.height(8.dp))

                        BasicTextField(
                            value = newFestivalName,
                            onValueChange = { newFestivalName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(Color.LightGray.copy(alpha = 0.2f))
                                .padding(16.dp),
                            decorationBox = { innerTextField ->
                                if (newFestivalName.isEmpty()) {
                                    Text(text = "Festival Name", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        )

                        BasicTextField(
                            value = newFestivalDate,
                            onValueChange = { newFestivalDate = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(Color.LightGray.copy(alpha = 0.2f))
                                .padding(16.dp),
                            decorationBox = { innerTextField ->
                                if (newFestivalDate.isEmpty()) {
                                    Text(text = "Festival Date (dd/mm/yyyy)", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        )

                        // Button to add the new festival
                        Button(
                            onClick = {
                                if (newFestivalName.isNotEmpty() && newFestivalDate.isNotEmpty()) {
                                    onAddFestival(newFestivalName, newFestivalDate)
                                    newFestivalName = ""
                                    newFestivalDate = ""
                                    showAddFestival = false
                                }
                                else{
                                    showAddFestival = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Submit Festival")
                        }
                    }
                }
            }

            // Button to toggle expand/collapse
            TextButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Hide Festivals" else "Show Festivals")
            }
        }
    }
}

@Composable
fun FestivalRow(festival: String, date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = festival,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = date, fontSize = 14.sp, color = Color.Gray)
    }
}

