package com.example.sdmw.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.ViewModel.AppViewModel


@Composable
fun PurchaseColumn(
    modifier: Modifier ,
                   purchases : List<PurchaseWithVendor> ,onArrowClicked : (PurchaseWithVendor)->Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "Purchases",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontSize = 32.sp,
            lineHeight = 36.sp
        )
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(text = "Your Purchases" ,
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 22.sp,
                lineHeight = 28.sp)
            Text(text = "Filters",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_semi_bold)),
                fontSize = 22.sp,
                lineHeight = 28.sp)
        }
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(purchases.size){
                    purchaseindex ->
                colors[purchaseindex%4]?.let {
                    PurchaseCard(
                        data = purchases[purchaseindex] ,
                        onArrowClicked = { onArrowClicked(purchases[purchaseindex]) },
                        bg_color = it,
                        Modifier.fillMaxWidth())
                }
            }
        }
    }
}



@Composable
fun PurchaseScreen(
                    purchases : List<PurchaseWithVendor>  ,
                    onIconClicked : (String)->Unit,
                    currnetScreen : String ,
                    onFABClicked: ()->Unit,
                    onArrowClicked: (PurchaseWithVendor) -> Unit){
    Scaffold(
        bottomBar = { BottomBar({ it->onIconClicked(it) } , currnetScreen) },
        floatingActionButton = { FloatingActionButton(onClick = onFABClicked) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        } }

    ) {it->
        PurchaseColumn(
            modifier = Modifier.padding(it),
            purchases = purchases ,{onArrowClicked(it)})
    }
}

