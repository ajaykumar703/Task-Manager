package com.example.sdmw.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.InitialPaymentDetails
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.Repository.Tax
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.ViewModel.AppViewModel

@Composable
fun PurchaseDetailsScreen(
    purchaseData: PurchaseWithVendor?,
    onClickBack: () -> Unit,
    onClickEdit: () -> Unit,
    viewModel: AppViewModel
) {
    Scaffold(
        topBar = {
            Topbar(
                onClickBack = onClickBack,
                secondtext = "Click on Edit Button To Edit the Purchase Details",
                Primarytext = "Purchase Details")
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                if (purchaseData != null) {
                    VendorDetailsSection(purchaseData.vendorDetails)
                }
            }
            item {
                if (purchaseData != null) {
                    PurchaseDetailsSection(purchaseData.purchaseData)
                }
            }
            item {
                if (purchaseData != null) {
                    TaxDetailsSection(purchaseData.purchaseData.tax)
                }
            }
            if (purchaseData != null && purchaseData.purchaseData.initialPay) {
                item {
                    InitialPaymentSection(purchaseData.purchaseData.initialPaymentDetails,
                        viewModel)
                }
            }
            item {
                Button(
                    onClick = onClickEdit,
                    modifier = Modifier,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(text = "Edit",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontWeight = FontWeight(500),
                        fontSize = 22.sp,
                        lineHeight = 26.sp,
                    )
                }
            }
        }
    }
}


@Composable
fun VendorDetailsSection(vendorDetails: VendorDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Vendor Details", fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,)
            Text("Name: ${vendorDetails.name}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("Address: ${vendorDetails.address}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("Contact: ${vendorDetails.contact}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        }
    }
}

@Composable
fun PurchaseDetailsSection(purchaseData: PurchaseData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Purchase Details", fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize =20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,)
            Text("Cost: ${purchaseData.cost}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("Discription: ${purchaseData.dis}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("Status: ${if (purchaseData.status) "Completed" else "Pending"}", fontFamily = FontFamily(Font(
                R.font.hk_grotesk_medium
            )),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("Purchase Date: ${purchaseData.purchaseDate}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
//            Text("Payment Date: ${purchaseData.payDate}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
//                fontSize = 16.sp,
//                fontWeight = FontWeight(500),
//                lineHeight = 20.sp,)
            Text("Reminder Date: ${purchaseData.remDate}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        }
    }
}

@Composable
fun TaxDetailsSection(tax: Tax) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Tax Details", fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                lineHeight = 24.sp,)
            Text("5% Tax: ₹${tax.fivePercent}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
            Text("12% Tax: ₹${tax.twelvePercent}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)
        }
    }
}

@Composable
fun InitialPaymentSection(
    initialPaymentDetails: InitialPaymentDetails?,
    viewModel: AppViewModel) {
    initialPaymentDetails?.let {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Initial Payment Details", fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 24.sp,)
                Text("Amount: ₹${initialPaymentDetails.amt}", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,)
                Text("Payment Mode: ${ viewModel.getCardById(initialPaymentDetails.card) }", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,)
//                if (initialPaymentDetails.imgDocs.isNotEmpty()) {
//                    Text("Attached Documents:", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight(500),
//                        lineHeight = 20.sp,)
////                    initialPaymentDetails.imgDocs.forEach { doc ->
////                        Text("- $doc", fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
////                            fontSize = 16.sp,
////                            fontWeight = FontWeight(500),
////                            lineHeight = 20.sp,)
////                    }
//                }
            }
        }
    }
}

