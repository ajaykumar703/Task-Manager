package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.sdmw.Repository.ExpenseCardData
import com.example.sdmw.ui.theme.Color1


@Composable
fun ExpenseCard(data : ExpenseCardData, bg_color : Color){

        Column(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    color = bg_color,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = data.title,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp , end = 12.dp)
                        .weight(1f),
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = 20.sp,
                    lineHeight = 28.sp
                )
                Icon(painter = painterResource(id = R.drawable.ic_bills),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp , end = 12.dp)
                        .clickable { })

            }
            Text(text = data.dis,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 15.sp,
                lineHeight = 20.sp
            )
            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(text = data.date,
                    modifier = Modifier.weight(1f),
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 13.sp,
                    lineHeight = 20.sp)
                Text(text = data.bank,
                    modifier = Modifier.weight(1f),
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                    fontSize = 15.sp,
                    lineHeight = 20.sp)
                Text(text = data.amt.toString(),
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_italic)),
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                    )
            }
        }

}

@Preview
@Composable
fun Preview(){
    ExpenseCard(data = ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC"), bg_color = Color1)
}