package com.example.sdmw.Repository

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseCardData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title : String,
    val date : String,
    val amt : Int,
    val dis : String,
    val bank : String,
    val mode : Int = 1
)



@Entity(tableName = "purchases")
data class PurchaseData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cost: String,
    val dis: String,
//    val imgDocs: List<String>,
    val initialPay: Boolean,
//    val payDate: String,
    val purchaseDate: String,
    val remDate: String,
    val status: Boolean,
    @Embedded val tax: Tax,
    val vendorId: Int,
    @Embedded val initialPaymentDetails: InitialPaymentDetails? = null,
    val cardId: Int? = null
)

data class InitialPaymentDetails(
    val amt: Int,
//    val imgDocs: List<String>,
    val card : Int
)

@Entity(tableName = "vendors")
data class VendorDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name : String,
    val address : String,
    val contact : String
)

data class Tax(
    val fivePercent: Double,
    val twelvePercent: Double
)

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name : String,
    val phone : String,
    val joiningDate : String,
    val salary : Int,
    val address : String,
    val department : String,
    val designation : String,
//    val absentDates : List<String>,
    val aadharnumber : String,
    @Embedded val emergencyContact : EmergencyContact,
    val status : Boolean,
    val notes: String? = null,
//    @Embedded val financialTransactions: List<FinancialTransactions>,
    var todayAttendance  : Int = 0
)

data class EmergencyContact(
    val ename : String,
    val ephone : String,
    val relation : String
)

data class FinancialTransactions(
    val date : String,
    val amt : Int,
    val reason : String,
    val repaymantStatus : Boolean,
)

@Entity(tableName = "Cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name : String ,
    val number : String,
    val cvv : String,
    val expiry : String,
    val brand : String = "VISA",
)


data class Today(
    val isAttendance : Boolean = false,
    val absents : Int = 0,
    val remainders : List<String> = emptyList(),
    val todayfests : List<String> = emptyList()
)