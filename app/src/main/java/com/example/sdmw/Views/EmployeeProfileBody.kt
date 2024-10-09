package com.example.sdmw.Views

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.EmergencyContact
import com.example.sdmw.Repository.Employee
import com.example.sdmw.Repository.FinancialTransactions


@Composable
fun ProfileBody(employee: Employee, modifier: Modifier) {
    val initialsBitmap: Bitmap = generateInitialsImage(employee.name)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = initialsBitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = CircleShape)
                .border(
                    BorderStroke(2.dp,
                        if(employee.todayAttendance != 0) Color.Green else Color.Red),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = employee.name,
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontWeight = FontWeight(600),
            fontSize = 20.sp,
            lineHeight = 24.sp,
            color = Color(0xFF303034)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = employee.designation,
            fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
            fontWeight = FontWeight(600),
            color = Color(0xFF303034),
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(
                icon = Icons.Default.Call,
                title = "",
                subtitle = "Leaves",
                bgColor = Color(0xFF72CFEC)
            )
//            InfoCard(
//                icon = Icons.Default.Close,
//                title = employee.absentDates.size.toString(),
//                subtitle = "Absences",
//                bgColor = Color(0xFF889DDA)
//            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phone, Address, Department
        EmployeeInfoSection(employee)

        Spacer(modifier = Modifier.height(16.dp))

        // Emergency Contact
        EmergencyContactCard(employee.emergencyContact)

        Spacer(modifier = Modifier.height(16.dp))

        // Financial Transactions
//        FinancialTransactionSection(employee.financialTransactions)
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, subtitle: String , bgColor: Color) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(124.dp)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, fontSize = 16.sp)
        }
    }
}

@Composable
fun EmployeeInfoSection(employee: Employee) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.1f
            )
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Phone: ${employee.phone}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Address: ${employee.address}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Department: ${employee.department}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Joining Date: ${employee.joiningDate}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Aadhar: ${employee.aadharnumber}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Status: ${if (employee.status) "Active" else "Inactive"}",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
fun EmergencyContactCard(contact: EmergencyContact) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Emergency Contact",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Name: ${contact.ename}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text(text = "Phone: ${contact.ephone}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text(text = "Relation: ${contact.relation}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        }
    }
}

@Composable
fun FinancialTransactionSection(transactions: List<FinancialTransactions>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.1f
            )
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Financial Transactions",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,

                )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // space between items
            ) {
                items(transactions) { transaction ->
                    Card(
                        modifier = Modifier
                            .width(300.dp) // Set a fixed width for the Card
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Date: ${transaction.date}",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp,
                            )
                            Text(
                                text = "Amount: ₹${transaction.amt}",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp,
                            )
                            Text(
                                text = "Reason: ${transaction.reason}",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp,
                            )
                            Text(
                                text = "Repayment Status: ${if (transaction.repaymantStatus) "Completed" else "Pending"}",
                                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                lineHeight = 20.sp,
                                color = if (transaction.repaymantStatus) Color.Green else Color.Red
                            )
                        }
                    }
                }
                item {
                    TransactionCard({})
                }

            }

        }
    }
}


