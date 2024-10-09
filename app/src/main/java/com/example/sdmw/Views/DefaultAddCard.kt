package com.example.sdmw.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.FinancialTransactions


@Composable
fun TransactionCard(onTransactionSubmitted: (FinancialTransactions) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(300.dp) // Set a fixed width for the Card
            .padding(vertical = 4.dp)
            .clickable { showDialog = true },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    )  {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Click here to add the details",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                color = Color.Gray
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        lineHeight = 20.sp,)
                }
            },
            title = {
                Text("Add Financial Transaction",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp,
                    lineHeight = 24.sp,)
            },
            text = {
                TransactionForm(
                    onSubmit = { transaction ->
                        onTransactionSubmitted(transaction)
                        showDialog = false
                    }
                )
            }
        )
    }
}

@Composable
fun TransactionForm(onSubmit: (FinancialTransactions) -> Unit) {
    var date by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }
    var repaymentStatus by remember { mutableStateOf(false) }

    Column(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                lineHeight = 20.sp,) }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                lineHeight = 20.sp,) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = reason,
            onValueChange = { reason = it },
            label = { Text("Reason",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                lineHeight = 20.sp,) }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Checkbox(
                checked = repaymentStatus,
                onCheckedChange = { repaymentStatus = it }
            )
            Text(text = "Repayment Status",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontWeight = FontWeight(600),
                fontSize = 14.sp,
                lineHeight = 20.sp,)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Validate and submit
            val amt = amount.toIntOrNull() ?: 0
            onSubmit(FinancialTransactions(date, amt, reason, repaymentStatus))
        }) {
            Text("Submit",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                lineHeight = 24.sp,)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTransactionCard() {
    TransactionCard(onTransactionSubmitted = { transaction ->
        println(transaction)
    })
}
