package com.example.sdmw.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.sdmw.Repository.ExpenseCardData
import com.example.sdmw.ui.theme.Color1
import com.example.sdmw.ui.theme.Color2
import com.example.sdmw.ui.theme.Color3
import com.example.sdmw.ui.theme.Color4


val colors = mapOf(
    0 to Color1,
    1 to Color2,
    2 to Color3,
    3 to Color4
)


@Composable
fun ExpenseColumn( expenses : List<ExpenseCardData>,
    modifier: Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "Expenses",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontFamily = FontFamily(Font(R.font.hk_grotesk_bold)),
            fontSize = 32.sp,
            lineHeight = 36.sp
        )
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(text = "Your Expenses" ,
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
            items(expenses.size){
                expenseindex ->
                colors[expenseindex%4]?.let {
                    ExpenseCard(data = expenses[expenseindex],
                        bg_color = it
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseScreen( expenses : List<ExpenseCardData>,
    onFABClicked : ()->Unit,
                  onBottomIconClicked:(String)->Unit,
                  currentScreen : String){
    Scaffold(
        bottomBar = { BottomBar({onBottomIconClicked(it)},currentScreen) },
        floatingActionButton = { FloatingActionButton(onClick = onFABClicked) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        } }
    ) {it->
        ExpenseColumn(expenses,Modifier.padding(it))
    }
}

