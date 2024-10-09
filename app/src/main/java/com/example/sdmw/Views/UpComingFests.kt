package com.example.sdmw.Views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.ViewModel.AppViewModel
import com.example.sdmw.ui.theme.Color1
import kotlinx.coroutines.delay



@Composable
fun UpComingFestsAnimation(
    viewModel: AppViewModel,
    modifier: Modifier,
) {
    var currentFestIndex by remember { mutableStateOf(0) }
    val festivalsByMonth by viewModel.festivalsByMonth.collectAsState()
    val fest = festivalsByMonth[2]



    // LaunchedEffect to cycle through the map every 2 seconds
    LaunchedEffect(currentFestIndex) {
        delay(2000)  // 2-second delay
        currentFestIndex = (currentFestIndex + 1) % fest.second.size  // Cycle back to the start
    }

    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .background(
                Color(0xFF124E78),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    )
    {
        // Animated content for sliding in from the top and sliding out to the bottom
        AnimatedContent(
            targetState = currentFestIndex,
            transitionSpec = {
                (slideInVertically(animationSpec = tween(500)) { height -> -height } + fadeIn()).togetherWith(
                    slideOutVertically(animationSpec = tween(500)) { height -> height } + fadeOut())
            }
        ) { targetIndex ->
            val (text, dateOrEmoji) = fest.second[targetIndex]

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = text,
                    style = TextStyle(fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontWeight = FontWeight(500),
                        color = Color.White)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = dateOrEmoji,
                    style = TextStyle(fontSize = if(dateOrEmoji.any({it.isDigit()})) 18.sp else 64.sp),
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontWeight = FontWeight(500),
                        color = Color.White)

            }
        }
    }
}
