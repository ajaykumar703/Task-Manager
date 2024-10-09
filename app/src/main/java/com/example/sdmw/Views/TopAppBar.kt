package com.example.sdmw.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R


@Composable
fun TopAppBar(
    heading : String,
    subheading : String,
){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)

    ) {
        Text(text = heading,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontSize = 32.sp,
            lineHeight = 36.sp,
            letterSpacing = 8.sp
        )
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(text = subheading ,
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 22.sp,
                lineHeight = 28.sp)
            Text(text = "Filters",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 22.sp,
                lineHeight = 28.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview(){
    TopAppBar("Heading","Subheading")
}

