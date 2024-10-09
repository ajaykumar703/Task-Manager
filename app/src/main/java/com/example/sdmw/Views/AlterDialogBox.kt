package com.example.sdmw.Views

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlterDialogBox(onDismiss: () -> Unit,
                   bodytext : String,
                   confirmText : String,
                   dismissText : String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Choose an Option", style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Text(
                bodytext,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = {
                // Handle camera option
                onDismiss()
            }) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                // Handle gallery option
                onDismiss()
            }) {
                Text(dismissText)
            }
        }
    )
}