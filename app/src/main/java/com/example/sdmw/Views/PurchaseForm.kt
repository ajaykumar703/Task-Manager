package com.example.sdmw.Views

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.Card
import com.example.sdmw.Repository.InitialPaymentDetails
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.Repository.Tax
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.ViewModel.AppViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun PurchaseForm( viewModel: AppViewModel, onClickBack: ()->Unit ,
                  purchaseData: PurchaseWithVendor?,
                  ){
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { Topbar(onClickBack = onClickBack , secondtext = "Enter the details or scan the invoice") },
        floatingActionButton = { FloatingActionButton(onClick = {
                openDialog.value = true
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_scan), contentDescription = null)
        } }
    ) {
        BodyPurchaseFormScreen(viewModel,Modifier.padding(it) , purchaseData )
        if (openDialog.value){
            AlterDialogBox(onDismiss = { openDialog.value = false },
                bodytext = "Choose the camera to scan the bill, or select an image from your gallery for the bill.",
                confirmText = "Gallery" , dismissText = "Camera")
        }
    }
}


fun parseDateToMillis(dateString: String): Long? {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(dateString)
        date?.time
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun addDaysToMillis(dateInMillis: Long, daysToAdd: Int): Long {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = dateInMillis
        add(Calendar.DAY_OF_YEAR, daysToAdd)
    }
    return calendar.timeInMillis
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyPurchaseFormScreen( viewModel: AppViewModel , modifier: Modifier ,
                            purchaseData: PurchaseWithVendor?,
                            ){

    var vendor by remember { mutableStateOf("") }
    var dis by remember { mutableStateOf("") }
    var totamt by remember { mutableStateOf("") }
    var bank by remember { mutableStateOf("") }
    var inipay by remember { mutableStateOf(false) }
    var purchasedate  by remember { mutableStateOf<Long?>(null) }
    var remdate  by remember { mutableStateOf<Long?>(null) }
    var status by remember { mutableStateOf(false) }
    var fivepercent by remember { mutableStateOf("") }
    var inipayamt by remember { mutableStateOf("") }
    var inipaymode by remember { mutableStateOf(-1) }
    var expanded by remember { mutableStateOf(false) }
    var expandedcard by remember { mutableStateOf(false) }
    var vendorid by remember { mutableStateOf(-1) }
    var cardId : Int? = null
    val showAlert = remember { mutableStateOf(false) }
    val vendors = viewModel.vendors.value ?: emptyList()
    val Cards = viewModel.cards.value ?: emptyList()


    if(showAlert.value){
        AlertDialog(
            onDismissRequest = { showAlert.value = false },
            confirmButton = {
                Button(onClick = { showAlert.value = false }) {
                    Text("OK")
                }
            },
            title = { Text("Error") },
            text = { Text("All fields are mandatory.") }
        )
    }

    purchaseData?.let {
        Log.d("ana","data is not null")
        vendor = it.vendorDetails.name
        vendorid = it.vendorDetails.id
        dis = it.purchaseData.dis
        totamt = it.purchaseData.cost
        purchasedate = parseDateToMillis(it.purchaseData.purchaseDate)
        remdate = parseDateToMillis(it.purchaseData.remDate)
        status = it.purchaseData.status
        inipay = it.purchaseData.initialPay
        if (it.purchaseData.initialPaymentDetails != null) {
            inipayamt = it.purchaseData.initialPaymentDetails.amt.toString()
            inipaymode = it.purchaseData.initialPaymentDetails.card
        }
    }


    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Log.d("ana","data is not null")

        OutlinedTextField(
                value = vendor,
                onValueChange = { vendor = it },
                label = { Text("Vendor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                readOnly = true, // Make it readonly to prevent manual typing
                trailingIcon = { Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown",
                    Modifier.clickable { expanded = !expanded }
                ) },
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = !expanded }
            ) {
                vendors.forEach { vendorDetails ->
                    DropdownMenuItem(
                        text = { Text(vendorDetails.name) },
                        onClick = {
                            vendor = vendorDetails.name
                            Log.d("ana","${vendorDetails.id} susmitha")
                            vendorid = vendorDetails.id
                            expanded = !expanded
                        }
                    )
                }
            }

        OutlinedTextField(value = dis,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(16.dp),
            onValueChange = {dis = it},
            label = { Text(text = "Description",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)},
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        )
        DatePicker("Purchase date",purchasedate,
            {   purchasedate = it
                remdate = it?.let { it1 -> addDaysToMillis(it1, 60) }
            }
        )
        DatePicker("Remainder date",remdate,{remdate = it})
        OutlinedTextField(value = totamt,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {totamt = it},
            label = { Text(text = "Total Amount",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)},
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        )
        OutlinedTextField(value = fivepercent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {fivepercent = it},
            label = { Text(text = "Tax 5%",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)},
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        )
        OutlinedTextField(value = fivepercent,  //calculate the 12% and assign
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = false,
            onValueChange = {fivepercent = it},
            label = { Text(text = "Tax 12%",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,)},
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                lineHeight = 20.sp,
            )
        )
        InitialPayment(iniPayAmt = inipayamt,
            oniniPayAmtChange = {inipayamt = it},
            oniniPayModeChange = {inipaymode = it},
            inipay,
            {inipay = it},
            Cards)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(BorderStroke(1.dp, if (status) Color(0xFF2A982F) else Color.Gray))
                .clickable { status = !status }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (status) "Done" else "Pending",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontWeight = FontWeight(500),
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                    color = if (status) Color(0xFF2A982F) else Color.Gray
                )
                if(status){
                    OutlinedTextField(
                        value = bank,
                        onValueChange = { bank = it },
                        label = { Text("Card") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        readOnly = true, // Make it readonly to prevent manual typing
                        trailingIcon = { Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown",
                            Modifier.clickable { expandedcard = !expandedcard }
                        ) },
                    )
                    DropdownMenu(
                        expanded = expandedcard,
                        onDismissRequest = { expandedcard = !expandedcard }
                    ) {
                        Cards.forEach { Card ->
                            DropdownMenuItem(
                                text = { Text(Card.name) },
                                onClick = {
                                    bank = Card.name
                                    cardId = Card.id
                                    expandedcard = !expandedcard
                                }
                            )
                        }
                    }
                DatePicker("Payment Date",remdate) { remdate = it }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                val formattedPD = DateFormatter(purchasedate)
                val formattedRD = DateFormatter(remdate)
                val data = listOf(
                    vendor,
                    dis,
                    totamt,
                    fivepercent,
                    formattedPD,
                    formattedRD,
                    vendorid.toString()
                ) + if (inipay) {
                    listOf(inipayamt, inipaymode.toString())
                } else {
                    listOf()
                }
                val isDataValid = validateFormData(data,showAlert)
                if(isDataValid){
                    viewModel.addPurchase(
                        PurchaseData(
                            vendorId = vendorid,
                            dis = dis,
                            cost = totamt,
                            tax = Tax(fivepercent.toDouble(), fivepercent.toDouble()),
                            initialPay = inipay,
                            initialPaymentDetails = if (inipay) InitialPaymentDetails(inipayamt.toInt(), inipaymode) else null,
                            purchaseDate = formattedPD,
                            remDate = formattedRD,
                            status = status
                        )
                    )


                }
            }){
                Text(text = "Add Purchase",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontWeight = FontWeight(500),
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                )
            }
        }

    }
}

@Composable
fun InitialPayment(iniPayAmt : String ,
                   oniniPayAmtChange : (String)->Unit,
                   oniniPayModeChange : (Int)->Unit,
                   have_inipay : Boolean,
                   oniniPayChange : (Boolean)->Unit,
                   Cards : List<Card>
) {


    var bank by remember { mutableStateOf("") }
    var expandedcard by remember { mutableStateOf(false) }
    var selectedCard : Card
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(
                width = 1.dp,
                color = if (have_inipay) Color(0xFF2A982F) else Color.Gray
            ),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Initial Payment",
                fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                fontWeight = FontWeight(500),
                fontSize = 22.sp,
                lineHeight = 26.sp,
            color = if(have_inipay) Color(0xFF2A982F) else Color.Gray,
            modifier = Modifier.clickable { oniniPayChange(!have_inipay) })

        if(have_inipay){
            OutlinedTextField(value = iniPayAmt,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onValueChange = {oniniPayAmtChange(it)},
                label = { Text(text = "Initial pay Amount",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,)},
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_medium)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 20.sp,
                )
            )
            OutlinedTextField(
                value = bank,
                onValueChange = { bank = it },
                label = { Text("Card") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                readOnly = true, // Make it readonly to prevent manual typing
                trailingIcon = { Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown",
                    Modifier.clickable { expandedcard = !expandedcard }
                ) },
            )
            DropdownMenu(
                expanded = expandedcard,
                onDismissRequest = { expandedcard = !expandedcard }
            ) {
                Cards.forEach { Card ->
                    DropdownMenuItem(
                        text = { Text(Card.name) },
                        onClick = {
                            selectedCard = Card
                            bank = Card.name
                            oniniPayModeChange(Card.id)
                            expandedcard = !expandedcard
                        }
                    )
                }
            }
        }
    }
}