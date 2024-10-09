package com.example.sdmw.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sdmw.Repository.Employee

@Composable
fun EmployeeDetailsScreen(
    employee: Employee,
    onClickBack: () -> Unit,
    onClickEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            Topbar(
                onClickBack = onClickBack,
                secondtext = "Click on Edit Button To Edit the Employee Details",
                Primarytext = "Employee Details"
            )
        }
    ){
          ProfileBody(employee = employee , modifier = Modifier.padding(it))
    }

}


