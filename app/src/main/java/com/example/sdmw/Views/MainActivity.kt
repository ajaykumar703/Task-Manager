package com.example.sdmw.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sdmw.ViewModel.DailyTaskManager
import com.example.sdmw.ui.theme.SDMWTheme

class MainActivity : ComponentActivity() {

    private lateinit var loginActivity: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loginActivity = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        setContent {
            SDMWTheme {
                NavController(loginActivity)
            }
        }
    }
}


