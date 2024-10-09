package com.example.sdmw.Views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.ExpenseCardData
import com.example.sdmw.ViewModel.AppViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun ExpenseFormScreen(onClickBack: () -> Unit,
                      viewModel: AppViewModel
){
    Scaffold(
        topBar = { Topbar(onClickBack = onClickBack , secondtext = "Enter the details of Expense") },
    ) {
        BodyExpenseFormScreen(Modifier.padding(it) ,
            viewModel
        )
    }
}

@Composable
fun BodyExpenseFormScreen(modifier: Modifier ,
                          viewModel: AppViewModel
) {
    var title by remember { mutableStateOf("") }
    var dis by remember { mutableStateOf("") }
    var amt by remember { mutableStateOf("") }
    var bank by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var mode by remember { mutableStateOf(1) } // 1 for Online, 0 for Cash
    val banks = listOf("Bank A", "Bank B", "Bank C")
    var expanded by remember { mutableStateOf(false) }
    val showAlert = remember { mutableStateOf(false) }


    if(showAlert.value){
        AlertDialog(
            onDismissRequest = { showAlert.value = false },
            confirmButton = {
                Button(onClick = { showAlert.value = false }) {
                    Text("OK")
                }
            },
            title = { Text("Error") },
            text = { Text("All fields are mandatory.") }
        )
    }

    Column {
        Column(modifier = modifier.weight(1f)) {
            // Title Input
            OutlinedTextField(
                value = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onValueChange = { title = it },
                label = {
                    Text(
                        text = "Title",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp
                )
            )

            // Description Input
            OutlinedTextField(
                value = dis,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(16.dp),
                onValueChange = { dis = it },
                label = {
                    Text(
                        text = "Description",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp
                )
            )

            // Amount Input
            OutlinedTextField(
                value = amt,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onValueChange = { amt = it },
                label = {
                    Text(
                        text = "Amount",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Payment Mode: ",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.width(8.dp))
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = { mode = 1; expanded = false },
                        text = {
                            Text(
                                "Online",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp
                            )
                        })
                    DropdownMenuItem(onClick = { mode = 0; expanded = false },
                        text = {
                            Text(
                                "Cash",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp
                            )
                        })
                }

                Text(
                    text = if (mode == 1) "Online" else "Cash",
                    modifier = Modifier.clickable { expanded = !expanded },
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )

            }

            // If mode is Online, show Bank selection
            if (mode == 1) {
                var bankExpanded by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = bank,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onValueChange = { bank = it },
                    label = {
                        Text(
                            text = "Select Bank",
                            fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            lineHeight = 20.sp
                        )
                    },
                    readOnly = true, // Bank selection should be from the dropdown
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.clickable { bankExpanded = true })
                    }
                )

                // Dropdown menu for banks
                DropdownMenu(
                    expanded = bankExpanded,
                    onDismissRequest = { bankExpanded = false }
                ) {
                    banks.forEach { bankName ->
                        DropdownMenuItem(onClick = { bank = bankName; bankExpanded = false },
                            text = {
                                Text(
                                    bankName,
                                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    lineHeight = 20.sp
                                )
                            })
                    }
                }
            }

            // Date Picker for selecting date of payment
            DatePicker("Date of payment", selectedDate, { selectedDate = it })

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val formattedDate = DateFormatter(selectedDate)
                val formData = listOf(title, dis, amt, bank, formattedDate)
                val isFormValid = validateFormData(formData, showAlert)

                if(isFormValid){
                    viewModel.addExpense(
                        ExpenseCardData(
                            title = title,
                            dis = dis,
                            amt = amt.toInt(),
                            bank = bank,
                            date = formattedDate,
                            mode = mode
                        )

                    )
                }
            }) {
                    Text(
                        text = "Add Expense",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontWeight = FontWeight(500),
                        fontSize = 22.sp,
                        lineHeight = 26.sp,
                    )
            }
        }
    }
}


//@Preview
//@Composable
//fun ExpenseFormScreenPreview() {
//    ExpenseFormScreen(onClickBack = {})
//}
