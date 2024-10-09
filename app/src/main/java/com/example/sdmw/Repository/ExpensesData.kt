package com.example.sdmw.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ExpenseRepository(context: Context){
//    private val expenses = mutableListOf(
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1),
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1),
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1),
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1),
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1),
//        ExpenseCardData(title = "Fee" , date = "12/12/24" , amt = 135000, dis = "Sagar's Collage Fee payment", bank = "HDFC", mode = 1)
//    )

//    fun getExpenses(): List<ExpenseCardData> {
//        return expenses
//    }

    private val expenseDao = AppDatabase.getDatabase(context).expenseDao()

    suspend fun getAllExpenses(): List<ExpenseCardData> {
        return withContext(Dispatchers.IO){
            expenseDao.getAllExpenses()
        }
    }

    suspend fun upsertExpense(expense: ExpenseCardData) {
        withContext(Dispatchers.IO) {
            expenseDao.upsertExpense(expense)
        }
    }

    suspend fun deleteExpense(expense: ExpenseCardData) {
        withContext(Dispatchers.IO) {
            expenseDao.deleteExpense(expense)
        }
    }

}