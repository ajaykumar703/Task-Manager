package com.example.sdmw.Views

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun NameRoleRow(
    name: String,
    role: String,
    onArrowClick: () -> Unit
) {
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
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow",
            tint = Color.Black,
            modifier = Modifier.clickable { onArrowClick() }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNameRoleRow() {
    NameRoleRow(
        name = "Sagar Eddumu",
        role = "Android Developer",
        onArrowClick = {
            // Action when the arrow button is clicked
            println("Arrow clicked!")
        }
    )
}
