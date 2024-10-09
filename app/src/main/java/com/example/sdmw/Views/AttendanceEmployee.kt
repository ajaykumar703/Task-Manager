package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sdmw.R
import com.example.sdmw.Repository.Employee
import com.example.sdmw.ViewModel.AppViewModel


@Composable
fun TopBarofAtt(onBackClicked : ()->Unit){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically // Aligns icon and text vertically at the center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onBackClicked() }
        )

        Spacer(modifier = Modifier.weight(1f)) // This Spacer will push the Text to the center

        Text(
            text = "ATTENDANCE",
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontSize = 32.sp,
            lineHeight = 36.sp,
            letterSpacing = 6.sp,
            modifier = Modifier.align(Alignment.CenterVertically) // Ensure the Text is vertically centered
        )

        Spacer(modifier = Modifier.weight(1f)) // Spacer for balancing the text in the center
    }
}

@Composable
fun AttendanceEmployeeList(onBackClicked: () -> Unit,
                           employees: List<Employee>,
                           onLockClikeded : ()->Unit,
                           viewModel: AppViewModel,
                           attendanceTaken : ()->Unit){
    Scaffold(
        topBar =  { TopBarofAtt(onBackClicked) }
    ) {it->
        Surface(
            shadowElevation = 16.dp,
            modifier = Modifier.padding(top = it.calculateTopPadding() + 16.dp , start = 16.dp , end = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            var isLocked by remember { mutableStateOf(true) }
            LazyColumn{
                items(employees){
                    EmployeeAttendanceItem(name = it.name, role = it.department ,
                        onAttendanceChanged = { todayatten-> it.todayAttendance = todayatten },
                        isLocked = isLocked,
                        it.todayAttendance)
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0x165F3F3F)
                    )
                }
                if(!viewModel.isAttendanceMarked() ){
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.White),
                            horizontalArrangement = Arrangement.Center,

                            ) {
                            Button(onClick = {
                                viewModel.markAttendance()
                                isLocked = !isLocked
                            }
                            ){
                                Text(text = "Take Attendance",
                                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                                    fontWeight = FontWeight(500),
                                    fontSize = 22.sp,
                                    lineHeight = 26.sp,
                                )
                            }
                        }
                    }
                }
                else{
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.White),
                            horizontalArrangement = Arrangement.Center,

                            ) {
                            Button(onClick = {
                                isLocked = !isLocked
                            }
                            ){
                                Text(text = if(isLocked) "Edit" else "Lock",
                                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                                    fontWeight = FontWeight(500),
                                    fontSize = 22.sp,
                                    lineHeight = 26.sp,
                                )
                            }
                        }
                    }
//                    item{
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                                .background(Color.White),
//                            horizontalArrangement = Arrangement.Center,
//
//                            ) {
//                            Button(onClick = { isLocked = !isLocked
////                            if(!viewModel.isAttendanceMarked()) onLockClikeded()
//                            }
//                            ){
//                                Text(text = "Lock",
//                                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
//                                    fontWeight = FontWeight(500),
//                                    fontSize = 22.sp,
//                                    lineHeight = 26.sp,
//                                )
//                            }
//                        }
//                    }
                }

            }
        }
    }
}

