package com.example.sdmw.Repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VendorRepository(context: Context) {

    private val vendorDao = AppDatabase.getDatabase(context).vendorDao()

    suspend fun getAllVendors(): List<VendorDetails> {
        return withContext(Dispatchers.IO) {
            vendorDao.getAllVendors()
        }
    }

    suspend fun upsertVendor(vendor: VendorDetails) {
        withContext(Dispatchers.IO) {
            vendorDao.upsertVendor(vendor)
        }
    }

    suspend fun deleteVendor(vendor: VendorDetails) {
        withContext(Dispatchers.IO) {
            vendorDao.deleteVendor(vendor)
        }
    }

    suspend fun getVendorById(id: Int): VendorDetails? {
        return vendorDao.getVendorById(id)
    }

    suspend fun PurchaseWithVendor(purchaseId : Int): PurchaseWithVendor {
        return vendorDao.PurchaseWithVendor(purchaseId)
    }

    suspend fun AllPurchaseWithVendor(): List<PurchaseWithVendor> {
        return vendorDao.getAllPurchasesWithVendors()

    }
}