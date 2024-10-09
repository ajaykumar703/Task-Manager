package com.example.sdmw.Views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.EmergencyContact
import com.example.sdmw.Repository.Employee
import com.example.sdmw.ViewModel.AppViewModel


@Composable
fun EmployeeForm(
    viewModel: AppViewModel,
    employee: Employee? = null,
    onClickBack: ()->Unit){
    Scaffold(
        topBar = { Topbar(onClickBack = onClickBack , secondtext = "Enter the details of the Employee") },
    ) {
           BodyEmployeeFormScreen( viewModel = viewModel,
               employeeData = employee,
               modifier = Modifier.padding(it))
    }
}

@Composable
fun BodyEmployeeFormScreen(
    viewModel: AppViewModel,
    employeeData: Employee?,
    modifier: Modifier
) {
    var name by remember { mutableStateOf(employeeData?.name?:  "") }
    var phone by remember { mutableStateOf(employeeData?.phone?: "") }
    var joiningDate by remember { mutableStateOf<Long?>(null) }
    var salary by remember { mutableStateOf(employeeData?.salary.toString()) }
    var address by remember { mutableStateOf(employeeData?.address?: "") }
    var department by remember { mutableStateOf(employeeData?.department?: "") }
    var designation by remember { mutableStateOf(employeeData?.designation?: "") }
//    var absentDates by remember { mutableStateOf("") }
    var aadharnumber by remember { mutableStateOf(employeeData?.aadharnumber?: "") }
    var emergencyName by remember { mutableStateOf(employeeData?.emergencyContact?.ename?: "") }
    var emergencyPhone by remember { mutableStateOf(employeeData?.emergencyContact?.ephone?: "") }
    var emergencyRelation by remember { mutableStateOf(employeeData?.emergencyContact?.relation?: "") }
    var status by remember { mutableStateOf(employeeData?.status?: false) }
    var notes by remember { mutableStateOf(employeeData?.notes) }
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

    employeeData?.let {
        joiningDate = parseDateToMillis(it.joiningDate)
    }

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(value = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { name = it },
            label = { Text("Name",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        OutlinedTextField(value = phone,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { phone = it },
            label = { Text("Phone",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        DatePicker("Joining Date", joiningDate) { joiningDate = it }

        OutlinedTextField(value = salary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { salary = it },
            label = { Text("Phone",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )

        OutlinedTextField(value = address,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { address = it },
            label = { Text("Address",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        OutlinedTextField(value = department,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { department = it },
            label = { Text("Department",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        OutlinedTextField(value = designation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { designation = it },
            label = { Text("Designation",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )

        OutlinedTextField(value = aadharnumber,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { aadharnumber = it },
            label = { Text("Aadhar Number",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        Text("Emergency Contact", modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            lineHeight = 24.sp,)
        OutlinedTextField(value = emergencyName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { emergencyName = it },
            label = { Text("Emergency Contact Name",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        OutlinedTextField(value = emergencyPhone,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { emergencyPhone = it },
            label = { Text("Emergency Contact Phone",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        OutlinedTextField(value = emergencyRelation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { emergencyRelation = it },
            label = { Text("Relation",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,) },
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(BorderStroke(1.dp, if (status) Color(0xFF2A982F) else Color.Gray))
                .clickable { status = !status }
        ) {
            Text(
                text = if (status) "Active" else "Inactive",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                color = if (status) Color(0xFF2A982F) else Color.Gray
            )
        }
        notes?.let {
            OutlinedTextField(value = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onValueChange = { notes = it },
                label = { Text("Notes",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,) },
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val data = listOf(
                    name, phone,
                    joiningDate.toString(),
                    salary,
                    address, department, designation, aadharnumber,
                    emergencyName, emergencyPhone, emergencyRelation
                )
                val isdataValid = validateFormData(data,showAlert)
                if(isdataValid){
                    viewModel.addEmployee(
                        Employee(
                            name = name,
                            phone = phone,
                            joiningDate = joiningDate.toString(),
                            salary = salary.toInt(),
                            address = address,
                            department = department,
                            designation = designation,
                            aadharnumber = aadharnumber,
                            emergencyContact = EmergencyContact(
                                ename = emergencyName,
                                ephone = emergencyPhone,
                                relation = emergencyRelation
                                ),
                            status = status,
                            notes = notes
                        )
                    )
                }
            }) {
                Text("Submit",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 26.sp,)
            }
        }
    }

}
