package com.example.sdmw.Repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PurchaseRepository(context: Context){

    private val purchaseDao = AppDatabase.getDatabase(context).purchaseDao()

    suspend fun getAllPurchases(): List<PurchaseData> {
        return withContext(Dispatchers.IO){
            purchaseDao.getAllPurchases()
        }
    }

    suspend fun upsertPurchase(purchase: PurchaseData) {
        withContext(Dispatchers.IO) {
            purchaseDao.upsertPurchase(purchase)
        }
    }
}