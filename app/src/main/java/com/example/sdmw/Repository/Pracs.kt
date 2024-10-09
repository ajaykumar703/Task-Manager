package com.example.sdmw.Repository

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserScreen(viewModel: FirebaseViewModel = viewModel()) {
    val userData by viewModel.userData.collectAsState()

    // Call Firebase function to fetch data
    LaunchedEffect(Unit) {
        viewModel.readUserData("exp1")
    }

    // Display the user data
    if (userData != null) {
        Text(text = "User Name: ${userData?.title}, Email: ${userData?.dis}")
    } else {
        Text(text = "Loading user data...",
            modifier = Modifier.clickable { viewModel.readUserData("exp1") })
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    UserScreen()
}
