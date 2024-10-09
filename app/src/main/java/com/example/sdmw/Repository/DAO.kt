package com.example.sdmw.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    suspend fun getAllExpenses(): List<ExpenseCardData>

    @Upsert
    suspend fun upsertExpense(expense: ExpenseCardData)

    @Delete
    suspend fun deleteExpense(expense: ExpenseCardData)

}

@Dao
interface PurchaseDao {


    @Query("SELECT * FROM purchases")
    suspend fun getAllPurchases(): List<PurchaseData>

    @Upsert
    suspend fun upsertPurchase(purchase: PurchaseData)

    @Delete
    suspend fun deletePurchase(purchase: PurchaseData)



}

@Dao
interface VendorDao {

    @Query("SELECT * FROM vendors")
    suspend fun getAllVendors(): List<VendorDetails>

    @Upsert
    suspend fun upsertVendor(vendor: VendorDetails)

    @Delete
    suspend fun deleteVendor(vendor: VendorDetails)

    @Query("SELECT * FROM vendors WHERE id = :vendorId")
    suspend fun getVendorById(vendorId: Int): VendorDetails?

    @Transaction
    @Query("SELECT * FROM purchases WHERE id = :purchaseId")
    suspend fun PurchaseWithVendor(purchaseId : Int): PurchaseWithVendor

    // Function to return all PurchaseWithVendor records
    @Transaction
    @Query("SELECT * FROM purchases")
    suspend fun getAllPurchasesWithVendors(): List<PurchaseWithVendor>

}

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    suspend fun getAllCards(): List<Card>

    @Upsert
    suspend fun upsertCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)

    @Query("SELECT * FROM cards WHERE id = :cardId")
    suspend fun getCardById(cardId: Int): Card?
}

data class PurchaseWithVendor(
    @Embedded val purchaseData: PurchaseData,
    @Relation(
        parentColumn = "vendorId",
        entityColumn = "id"
    )
    val vendorDetails: VendorDetails
)

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    suspend fun getAllEmployees(): List<Employee>

    @Upsert
    suspend fun upsertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees WHERE id = :employeeId")
    suspend fun getEmployeeById(employeeId: Int): Employee?

}