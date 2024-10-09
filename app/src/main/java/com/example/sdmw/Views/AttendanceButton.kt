package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.ui.theme.Color1
import com.example.sdmw.ui.theme.Color2

@Composable
fun AttendanceSquare(attendanceTaken: Boolean,
                     totalEmployees: Int,
                     presentEmployees: Int,
                     halfEmployees: Int,
                     modifier: Modifier,
                     onClick : ()->Unit ) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .background(
                Color(0xFF93dbfa),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (!attendanceTaken) {

            Text(
                text = "Attendance\nnot\ntaken",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        } else {
            val absentEmployees = totalEmployees - presentEmployees
            val attendancePercentage = presentEmployees.toFloat() / totalEmployees * 100
            val absentPercentage = absentEmployees.toFloat() / totalEmployees * 100
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .drawBehind {
                        // Gradient for Absent percentage (Red-like gradient)
                        val absentGradient = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFF5A5F),
                                Color(0xFFFFC371)
                            ) // Red to Orange gradient
                        )
                        // Gradient for Attendance percentage (Green-like gradient)
                        val attendanceGradient = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF56ab2f),
                                Color(0xFFa8e063)
                            ) // Green to Light Green gradient
                        )

                        // Drawing absent percentage arc
                        drawArc(
                            brush = absentGradient, // Using the gradient brush
                            startAngle = -90f,
                            sweepAngle = absentPercentage / 100 * 360f,
                            useCenter = false,
                            style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
                        )
                        // Drawing attendance percentage arc
                        drawArc(
                            brush = attendanceGradient, // Using the gradient brush
                            startAngle = -90f + (absentPercentage / 100 * 360f),
                            sweepAngle = attendancePercentage / 100 * 360f,
                            useCenter = false,
                            style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "$presentEmployees"+if(halfEmployees>0) "(${halfEmployees})" else "",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontSize = 32.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp,
                        color = Color.White)
                    HorizontalDivider(
                        thickness = 3.dp,
                        color = Color(0x165F3F3F),
                        modifier = Modifier.width(100.dp)
                    )
                    Text(text = "$totalEmployees",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontSize = 32.sp,
                        fontWeight = FontWeight(500),
                        lineHeight = 20.sp,
                        color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttendanceSquarePreview() {
    AttendanceSquare(attendanceTaken = false, totalEmployees = 100, presentEmployees = 80,halfEmployees = 10,
        modifier = Modifier,{})
}

