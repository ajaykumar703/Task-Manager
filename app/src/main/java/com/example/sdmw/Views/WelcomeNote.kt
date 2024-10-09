package com.example.sdmw.Views

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sdmw.R


@Preview
@Composable
fun ShiningHorizontalRainbowText() {

    val gradientColors = listOf(
        Color(0xFF00C2CB), // Teal
        Color(0xFFFFD84B), // Yellow
        Color(0xFFFF6A4B), // Orange
        Color(0xFFDA5CE1), // Pink
        Color(0xFF4B66F7), // Blue
    )

    BasicText(
        text = "Welcome,\nBhaskar",
        style = androidx.compose.ui.text.TextStyle(
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontSize = 48.sp,
            fontWeight = FontWeight(700),
            lineHeight = 50.sp,
            brush = Brush.linearGradient(
                colors = gradientColors,
            )
        )
    )
}
