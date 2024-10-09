package com.example.sdmw.Views

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R

@Composable
fun EmployeeAttendanceItem(
    name: String,
    role: String,
    onAttendanceChanged: (Int) -> Unit,
    isLocked: Boolean,
    todayAttendance: Int // This holds the current attendance state (0 = absent, 1 = full day, 2 = half day)
) {
    var isFullDay by remember { mutableStateOf(todayAttendance == 1) }
    var isHalfDay by remember { mutableStateOf(todayAttendance == 2) }

    val initialsBitmap: Bitmap = generateInitialsImage(name)



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            bitmap = initialsBitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,
            )
            Text(
                text = role,
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        if (todayAttendance == 0 && isLocked) {

            Text(
                text = "Absent",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color.Red,
                modifier = Modifier.padding(end = 8.dp)
            )
        } else {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Half Day",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = if (isHalfDay) 16.sp else 14.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,
                    color = Color(0xFFA52A78)
                )
                Switch(
                    checked = isHalfDay,
                    enabled = !isLocked,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.Gray,
                        checkedTrackColor = Color(0xFFA52A78),
                        uncheckedTrackColor = Color(0x165F3F3F),
                        disabledCheckedThumbColor = Color(0xFFA52A78),
                        disabledCheckedIconColor = Color(0xFFA52A78),
                        disabledCheckedTrackColor = Color(0xFFA52A78),
                    ),
                    onCheckedChange = { checked ->
                        isHalfDay = checked
                        if (checked) isFullDay = false
                        onAttendanceChanged(if (checked) 2 else if (isFullDay) 1 else 0)
                    }
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Full Day",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = if (isFullDay) 16.sp else 14.sp, // Make text larger if Full Day
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,
                    color = Color(0xFF36A52A)
                )
                Switch(
                    checked = isFullDay,
                    enabled = !isLocked,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.Gray,
                        checkedTrackColor = Color(0xFF9EDB9E),
                        uncheckedTrackColor = Color(0x165F3F3F),
                        disabledCheckedThumbColor = Color(0xFF9EDB9E),
                        disabledCheckedIconColor = Color(0xFF9EDB9E),
                        disabledCheckedTrackColor = Color(0xFF9EDB9E)
                    ),
                    onCheckedChange = { checked ->
                        isFullDay = checked
                        if (checked) isHalfDay = false
                        onAttendanceChanged(if (checked) 1 else if (isHalfDay) 2 else 0)
                    }
                )
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewEmployeeAttendanceItem(){
    EmployeeAttendanceItem(
        name = "Sagar Eddumu",
        role = "Android Developer",
        onAttendanceChanged = {},
        true,
        0
        )

}