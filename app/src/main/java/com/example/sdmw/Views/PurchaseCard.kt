package com.example.sdmw.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.ViewModel.AppViewModel
import java.text.SimpleDateFormat
import java.util.*

fun daysBetweenDates(endDate: String): Long {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val start = Date()
    val end = dateFormat.parse(endDate)
    val differenceInMillis = end.time - start.time
    return differenceInMillis / (1000 * 60 * 60 * 24)
}

@Composable
fun PurchaseCard(
    data : PurchaseWithVendor,
    onArrowClicked : ()->Unit = {},
    bg_color : Color,
    modifier: Modifier,
    ){

        Column(
        modifier = modifier
            .padding(4.dp)
            .background(
                color = bg_color,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                    text = data.vendorDetails.name,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        .weight(1f),
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = 20.sp,
                    lineHeight = 28.sp
                )

            Icon(painter = painterResource(id = R.drawable.ic_bills),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .clickable { })

        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = data.purchaseData.dis,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 15.sp,
                lineHeight = 20.sp,
            )
            Icon(imageVector = Icons.Default.ArrowForward,
                modifier = Modifier
                    .padding(top = 12.dp , end = 12.dp)
                    .clickable { onArrowClicked() },
                contentDescription = null)
        }
        Row(
            modifier = Modifier.padding(12.dp),
        ) {
            Text(text = data.purchaseData.purchaseDate,
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 13.sp,
                lineHeight = 20.sp)
            Text(text = daysBetweenDates(data.purchaseData.remDate).toString(),
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 15.sp,
                lineHeight = 20.sp)
            Text(text = data.purchaseData.cost,
                fontFamily = FontFamily(Font(R.font.hk_grotesk_italic)),
                fontSize = 15.sp,
                lineHeight = 20.sp
            )
        }
    }

}

