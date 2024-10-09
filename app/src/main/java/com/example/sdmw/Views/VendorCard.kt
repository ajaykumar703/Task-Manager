package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.ui.theme.Color1

@Composable
fun VendorCard(data : VendorDetails,
               onEditClick : (VendorDetails)->Unit,
               bg_color : Color
){
    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 2.dp)
            .fillMaxWidth()
            .background(
                color = bg_color,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = data.name,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 20.sp,
                lineHeight = 28.sp
            )
            Icon(painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .clickable { onEditClick(data) })

        }
        Text(text = data.contact,
            modifier = Modifier.padding(top = 8.dp, start = 12.dp),
            fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
            fontSize = 15.sp,
            lineHeight = 20.sp
        )

        Text(text = data.address,
            modifier = Modifier.padding(top = 8.dp, start = 12.dp, bottom = 12.dp),
            fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
            fontSize = 15.sp,
            lineHeight = 20.sp,
        )
    }
}

