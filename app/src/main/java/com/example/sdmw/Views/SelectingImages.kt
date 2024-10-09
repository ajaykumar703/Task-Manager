package com.example.sdmw.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun RequestPermissionandProceed(
    permission: String,
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
    onRequestPermission: () -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    HandleRequest(
        permissionState = permissionState,
        onRequestPermission = onRequestPermission,
        rationaleMessage = rationaleMessage
    )
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    onRequestPermission: () -> Unit,
    rationaleMessage: String
) {
    when {
        permissionState.hasPermission -> {
            // Permission is granted, proceed with your app functionality
            Text("Permission Granted")
        }
        permissionState.shouldShowRationale -> {
            // Show a rationale dialog explaining why the permission is needed
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Permission Request",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                text = {
                    Text(rationaleMessage)
                },
                confirmButton = {
                    Button(onClick = onRequestPermission) {
                        Text("Give Permission")
                    }
                }
            )
        }
        else -> {
            // Directly show a button to request permission
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Permission Denied")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onRequestPermission) {
                    Text("Request Permission")
                }
            }
        }
    }
}
