package com.example.sdmw.Views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sdmw.Repository.Employee


@Composable
fun EmployeeList(employees: List<Employee>,
    onIconClicked : (String)->Unit,
                   currnetScreen : String ,
                   onFABClicked: ()->Unit,
                   onArrowClicked: (Employee) -> Unit){

    Scaffold(
        topBar = { TopAppBar(heading = "Employees", subheading = "Employees List") },
        bottomBar = { BottomBar({ onIconClicked(it) } , currnetScreen) },
        floatingActionButton = { FloatingActionButton(onClick = onFABClicked) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        } }

    ) {it->
        Surface(
            shadowElevation = 16.dp,
            modifier = Modifier.padding(top = it.calculateTopPadding() + 16.dp , start = 16.dp , end = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(employees){
                    NameRoleRow(name = it.name, role = it.department,{ onArrowClicked(it) })
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0x165F3F3F)
                    )
                }
            }
        }
    }
}

