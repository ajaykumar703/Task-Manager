package com.example.sdmw.Views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R


@Preview(showBackground = true)
@Composable
fun Store() {
    var isMensWear by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .width(180.dp)
            .clickable {
                isMensWear = !isMensWear
            }
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(8.dp))
            .background(
                color = Color(0xFF27A5C4)
            ),
    ) {
        AnimatedContent(
            modifier = Modifier.padding(8.dp),
            targetState = isMensWear,
            transitionSpec = {
                (slideInVertically(animationSpec = tween(400)) { height -> -height } + fadeIn()).togetherWith(
                    slideOutVertically(animationSpec = tween(500)) { height -> height } + fadeOut())
            },
            label = "", ) { targetState ->
            if (targetState) {
                BasicText(
                    text = "SD Mens Wear",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            } else {
                BasicText(
                    text = "SD Uth Club",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            }
        }
    }
}
