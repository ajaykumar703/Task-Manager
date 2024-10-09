package com.example.sdmw.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R

@Composable
fun Topbar(onClickBack : ()->Unit , secondtext : String , Primarytext : String = "Add Details") {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 16.dp, bottom = 10.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically


    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier.clickable { onClickBack() })
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = Primarytext,
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xFF303034)
            )
            Text(
                text = secondtext,
                fontFamily = FontFamily(Font(R.font.hk_grotesk_regular)),
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                lineHeight = 16.sp,
                color = Color(0xFF303034)
            )
        }
    }
}

@Preview
@Composable
fun TopbarPreview(){
    Topbar({},"Add Details")
}