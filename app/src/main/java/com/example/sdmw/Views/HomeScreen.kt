package com.example.sdmw.Views

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sdmw.R
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.ViewModel.AppViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun HomeScreenTopBar(onMenuClicked : () -> Unit = {} ){
     Row(
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier
             .padding(top =32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
             .fillMaxWidth()
     ) {
         Image(
             painter = painterResource(id = R.drawable.profileavatar),
             contentDescription = "Menu",
             modifier = Modifier
                 .clickable { onMenuClicked() }
                 .clip(RoundedCornerShape(12.dp)) // Curved corners with 12dp radius
                 .shadow(8.dp, shape = RoundedCornerShape(12.dp)) // Adds elevation
         )

         Spacer(modifier = Modifier.weight(1f))
         Store()
     }
}


@Composable
fun HomeScreen(onIconClicked : (String)->Unit ,
               currnetScreen : String,
               onLogoutClicked : ()->Unit,
               purchases : List<PurchaseWithVendor>,
               onAttendanceClicked : ()->Unit,
               todayAttendance : Boolean,
               viewModel: AppViewModel,
               onVendorClicked: () -> Unit,
               onRemainderArrowClicked : (PurchaseWithVendor)->Unit,
               onCardClicked : ()->Unit,
               onFestivalClicked : ()->Unit,
               totalEmployees : Int
){

    val DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = DrawerState,
        drawerContent = { DrawerContent(
            onVendorClicked = onVendorClicked,
            onBankClicked = { onCardClicked() },
            onFestivalClicked = onFestivalClicked
        ) }
    ){
        Scaffold(
            topBar = { HomeScreenTopBar({scope.launch { DrawerState.open() }}) },
            bottomBar = { BottomBar({ onIconClicked(it) } , currnetScreen) }
        ) {


            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                ShiningHorizontalRainbowText()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()  // Ensures Row takes full width of the parent
                ) {
                    UpComingFestsAnimation(
                        viewModel,
                        modifier = Modifier
                            .weight(1f)  // Equal width to AttendanceSquare
                            .aspectRatio(1f),
                    )
                    val AttendanceStats = viewModel.CalculatePresentEmployees()
                AttendanceSquare(
                    attendanceTaken = todayAttendance,
                    totalEmployees = totalEmployees,
                    presentEmployees = AttendanceStats.first,
                    halfEmployees = AttendanceStats.second,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f),
                    {onAttendanceClicked()}
                )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0x165F3F3F), // Darker by increasing opacity (66 = 40% opacity)
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Remainders",
                        fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        color = Color(0x695F3F3F), // Darker by increasing opacity (99 = 60% opacity)
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color(0x165F3F3F), // Darker by increasing opacity (66 = 40% opacity)
                        modifier = Modifier.weight(1f)
                    )
                }
            Remainders(
                purchases = purchases,
                onArrowClicked = onRemainderArrowClicked
            )

            }

        }
    }
}

@Composable
fun Logout(loginActivity: SharedPreferences,
           onLogoutClicked : ()->Unit){
    Button(onClick = { loginActivity.edit().putInt("isLogged",0).apply()
        onLogoutClicked()
    }) {
        Text( text = "Logout")
    }
}

@Composable
fun DrawerContent(
//    onLogoutClicked: () -> Unit,
    onVendorClicked: () -> Unit,
    onBankClicked: () -> Unit,
    onFestivalClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp) // Adjust width for a professional look
            .background(color = Color.White)
    ) {
        // Profile Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top =32.dp, start = 12.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.profileavatar),
                contentDescription = "Menu",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)) // Curved corners with 12dp radius
                    .shadow(8.dp, shape = RoundedCornerShape(12.dp)) // Adds elevation
            )
            Spacer(modifier = Modifier.width(16.dp))
            // User Name

                Text(
                    text = "Bhaskar.E",
                    fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
                    fontWeight = FontWeight(500),
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                )

        }

        Spacer(modifier = Modifier.height(24.dp)) // Add spacing before options

        // Option List Section using LazyColumn for scrolling behavior
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
        ) {
            item {
                DrawerOptionItem(
                    text = "Vendors",
                    icon = painterResource(id = R.drawable.ic_vendors),
                    onNameClicked = {onVendorClicked()}
                )
            }
            item {
                DrawerOptionItem(
                    text = "Cards",
                    icon = painterResource(id = R.drawable.ic_card),
                    onNameClicked = {onBankClicked()}
                )
            }

            item {
                DrawerOptionItem(
                    text = "Festivals",
                    icon = painterResource(id = R.drawable.ic_fests),
                    onNameClicked = { onFestivalClicked() }
                )
            }

            item {
                DrawerOptionItem(
                    text = "Logout",
                    icon = painterResource(id = R.drawable.ic_signout),
                    textColor = Color.Red,
                    iconColor = Color.Red,
                    onNameClicked = {}
                )
            }
        }
    }
}

@Composable
fun DrawerOptionItem(
    text: String,
    icon: Painter,
    iconColor: Color = Color(0xFF7C46D5),
    textColor: Color = Color.Black,
    onNameClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNameClicked() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = textColor,
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontWeight = FontWeight(500),
            fontSize = 22.sp,
            lineHeight = 26.sp,
        )
    }
}
