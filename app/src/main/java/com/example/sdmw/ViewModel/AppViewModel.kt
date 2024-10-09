package com.example.sdmw.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sdmw.Repository.Card
import com.example.sdmw.Repository.CardsRepository
import com.example.sdmw.Repository.Employee
import com.example.sdmw.Repository.EmployeeRepository
import com.example.sdmw.Repository.ExpenseCardData
import com.example.sdmw.Repository.ExpenseRepository
import com.example.sdmw.Repository.FestivalRepository
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseRepository
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.Repository.VendorDetails
import com.example.sdmw.Repository.VendorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val employeerepository = EmployeeRepository(application)
    private val purchaseRepository = PurchaseRepository(application)
    private val expenseRepository = ExpenseRepository(application)
    private val vendorRepository = VendorRepository(application)
    private val cardRepository = CardsRepository(application)
    private val festivalRepo = FestivalRepository()
    private val attendanceRepository = DailyTaskManager(application)



    private val _employees = MutableLiveData<List<Employee>>()
    private val _expenses = MutableLiveData<List<ExpenseCardData>>()
    private val _vendors = MutableLiveData<List<VendorDetails>>()
    private val _cards = MutableLiveData<List<Card>>()
    private val _purchasesWithVendor = MutableLiveData<List<PurchaseWithVendor>>()
    private val _Cardwithid = MutableLiveData<Card?>()
    private val _festivalsByMonth = MutableStateFlow(festivalRepo.festivalsByMonth)





    val employees: LiveData<List<Employee>> get() = _employees
    val expenses: LiveData<List<ExpenseCardData>> get() = _expenses
    val vendors: LiveData<List<VendorDetails>> get() = _vendors
    val cards: LiveData<List<Card>> get() = _cards
    val purchasesWithVendor: LiveData<List<PurchaseWithVendor>> get() = _purchasesWithVendor
    val Cardwithid: LiveData<Card?> = _Cardwithid
    val festivalsByMonth: MutableStateFlow<List<Pair<String, MutableList<Pair<String, String>>>>> = _festivalsByMonth


    init {
        fetchAllEmployees()
        fetchAllExpenses()
        fetchAllVendors()
        fetchAllCards()
        AllPurchasesWithVendor()
        attendanceRepository.performDailyTask()
    }

    private fun fetchAllEmployees() {
        viewModelScope.launch {
            _employees.value = employeerepository.getAllEmployees()
        }
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch {
            employeerepository.upsertEmployee(employee)
            fetchAllEmployees()
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            employeerepository.deleteEmployee(employee)
            fetchAllEmployees()
        }
    }

    private fun fetchAllCards() {
        viewModelScope.launch {
            _cards.value = cardRepository.getAllCards()
        }
    }

    fun getCardById(cardId: Int): Card? {
        viewModelScope.launch {
            _Cardwithid.postValue(cardRepository.getCardById(cardId))
        }
        return Cardwithid.value
    }

    fun addCard(card: Card) {
        viewModelScope.launch {
            cardRepository.upsertCard(card)
            fetchAllCards()
        }
    }



    fun AllPurchasesWithVendor() {
        viewModelScope.launch {
             _purchasesWithVendor.value = vendorRepository.AllPurchaseWithVendor()
        }
    }
    private fun fetchAllVendors() {
        viewModelScope.launch {
            _vendors.value = vendorRepository.getAllVendors()
        }
    }

//    fun getVendorById(vendorId: Int) {
//        viewModelScope.launch {
//            val vendor = vendorRepository.getVendorById(vendorId)
//            _Vendorwithid.postValue(vendor)
//
//        }
//
//    }

    fun addVendor(vendor: VendorDetails) {
        viewModelScope.launch {
            vendorRepository.upsertVendor(vendor)
            fetchAllVendors()
        }
    }

//    private fun fetchAllPurchases() {
//        viewModelScope.launch {
//            _purchases.value = purchaseRepository.getAllPurchases()
//        }
//    }

    fun addPurchase(purchase: PurchaseData) {
        viewModelScope.launch {
            purchaseRepository.upsertPurchase(purchase)
            AllPurchasesWithVendor()
        }
    }
    // if there is no vendor with the given ID, create a new one and then add the purchase

    private fun fetchAllExpenses() {
        viewModelScope.launch {
            _expenses.value = expenseRepository.getAllExpenses()
        }
    }

    fun addExpense(expense: ExpenseCardData) {
        viewModelScope.launch {
            expenseRepository.upsertExpense(expense)
            fetchAllExpenses()
        }
    }

    fun deleteExpense(expense: ExpenseCardData) {
        viewModelScope.launch {
            expenseRepository.deleteExpense(expense)
            fetchAllExpenses()
        }
    }

    fun CalculatePresentEmployees(): Pair<Int, Int> {
        var presentcount = 0
        var halfcount = 0
        employees.value?.forEach {
            if (it.todayAttendance == 1) presentcount++
            else if (it.todayAttendance == 2) halfcount++
        }
        return Pair(presentcount, halfcount)
    }

    fun addFestival(month: String, festival: String, date: String) {
        // Find the month and add the festival to the mutable list of festivals
        viewModelScope.launch {
            festivalsByMonth.value = festivalsByMonth.value.toMutableList().apply {
                find { it.first == month }?.second?.add(festival to date)
            }
        }
    }

    fun isAttendanceMarked(): Boolean {
        return attendanceRepository.isAttendanceMarked()
    }

    fun markAttendance() {
        attendanceRepository.markAttendance()
    }


}
