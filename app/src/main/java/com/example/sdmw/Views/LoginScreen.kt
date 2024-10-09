package com.example.sdmw.Views

import android.content.SharedPreferences
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LoginScreen(loginActivity: SharedPreferences , navController: NavController){

    Button(onClick = { loginActivity.edit().putInt("isLogged",1).apply()
        navController.navigate(Homescreen)
    }) {

        Text(text = "Login")

    }

}