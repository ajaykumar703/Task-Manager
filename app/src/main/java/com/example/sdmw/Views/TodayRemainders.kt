package com.example.sdmw.Views

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.ViewModel.AppViewModel

@Composable
fun Remainders(
    purchases: List<PurchaseWithVendor>,
    onArrowClicked: (PurchaseWithVendor) -> Unit
)
{
    LazyRow{
            items(purchases.size){
                    purchaseindex ->
                colors[purchaseindex%4]?.let {
                    PurchaseCard(
                        data = purchases[purchaseindex] ,
                        onArrowClicked = { onArrowClicked(purchases[purchaseindex]) },
                        bg_color = it,
                         Modifier.width(300.dp),

                    )
                }
            }
        }

}
