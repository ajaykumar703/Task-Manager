package com.example.sdmw.Views

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sdmw.Repository.Employee
import com.example.sdmw.Repository.PurchaseData
import com.example.sdmw.Repository.PurchaseWithVendor
import com.example.sdmw.ViewModel.AppViewModel
import kotlinx.serialization.Serializable

@Serializable
object Startpoint

@Serializable
object Homescreen

@Serializable
object Expensescreen

@Serializable
object Purchasescreen

@Serializable
object Expenseform

@Serializable
object purchaseformwithdata

@Serializable
object purchaseformwithnull

@Serializable
object Purchasedetailsscreen

@Serializable
object StaffScreen

@Serializable
object Staffdetailsscreen

@Serializable
object Staffformwithdata

@Serializable
object Staffformwithnull

@Serializable
object Attendancescreen

@Serializable
object VendorScreen

@Serializable
object BankCardScreen

@Serializable
object FestivalScreen

@Composable
fun NavController(loginActivity: SharedPreferences) {

    val viewModel: AppViewModel = viewModel()
    val employees by viewModel.employees.observeAsState(emptyList())
    val expenses by viewModel.expenses.observeAsState(emptyList())
    val allPurchaseswithVendor by viewModel.purchasesWithVendor.observeAsState(emptyList())




    fun isLoggedIn(): Int {
        return loginActivity.getInt("isLogged", 0)
    }



    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf("homeScreen") }
    var purchaseData: PurchaseWithVendor? = null
    var employee: Employee? = null

    fun bottomNavigation(it: String) {
        when (it) {
            "homeScreen" -> {
                navController.navigate(Homescreen)
                currentScreen = "homeScreen"
            }
            "purchaseScreen" -> {
                navController.navigate(Purchasescreen)
                currentScreen = "purchaseScreen"
            }
            "expenseScreen" -> {
                navController.navigate(Expensescreen)
                currentScreen = "expenseScreen"
            }
            "staffScreen" -> {
                navController.navigate(StaffScreen)
                currentScreen = "staffScreen"
            }
        }
    }

    NavHost(navController = navController, startDestination = Startpoint) {
        composable<Startpoint> {
            if (isLoggedIn() == 0)
                LoginScreen(loginActivity, navController)
            else
                HomeScreen(
                   onIconClicked =  {  bottomNavigation(it) },
//                    loginActivity,
                    currnetScreen = currentScreen,
                   onLogoutClicked = { navController.navigate(Startpoint) },
                   purchases =  allPurchaseswithVendor,
                   onAttendanceClicked =  { navController.navigate(Attendancescreen)},
                   todayAttendance =  viewModel.isAttendanceMarked(),
                   viewModel =  viewModel,
                   onVendorClicked =  {navController.navigate(VendorScreen)},
                    onRemainderArrowClicked = {
                        purchaseData = it
                        navController.navigate(Purchasedetailsscreen)
                    },
                    onCardClicked = { navController.navigate(BankCardScreen) },
                    onFestivalClicked = { navController.navigate(FestivalScreen) },
                    totalEmployees = employees.size
                )
        }

        composable<Homescreen> {
            HomeScreen(
                onIconClicked =  {  bottomNavigation(it) },
//                    loginActivity,
                currnetScreen = currentScreen,
                onLogoutClicked = { navController.navigate(Startpoint) },
                purchases =  allPurchaseswithVendor,
                onAttendanceClicked =  { navController.navigate(Attendancescreen)},
                todayAttendance =  viewModel.isAttendanceMarked(),
                viewModel =  viewModel,
                onVendorClicked =  {navController.navigate(VendorScreen)},
                onRemainderArrowClicked = {
                    purchaseData = it
                    navController.navigate(Purchasedetailsscreen)
                },
                onCardClicked = { navController.navigate(BankCardScreen) },
                onFestivalClicked = { navController.navigate(FestivalScreen) },
                totalEmployees = employees.size
            )
        }

        composable<Expensescreen> {
            ExpenseScreen(
                expenses = expenses,
                onFABClicked = { navController.navigate(Expenseform) },
                onBottomIconClicked = { bottomNavigation(it) },
                currentScreen = currentScreen
            )
        }
        composable<purchaseformwithdata> {
            PurchaseForm(
                viewModel = viewModel,
                onClickBack = { navController.navigate(Purchasescreen) },
                purchaseData = purchaseData,

            )
        }
        composable<purchaseformwithnull> {
            PurchaseForm(
                viewModel = viewModel,
                onClickBack = { navController.navigate(Purchasescreen) },
                purchaseData = null,
            )
        }

        composable<Purchasescreen> {
            PurchaseScreen(
                purchases = allPurchaseswithVendor,
                onIconClicked = {bottomNavigation(it) },
                currnetScreen = currentScreen,
                onFABClicked = { navController.navigate(purchaseformwithnull) },
                onArrowClicked = { purchaseData = it
                    navController.navigate(Purchasedetailsscreen)
                }
            )
        }

        composable<Expenseform> {
            ExpenseFormScreen(
                onClickBack = { navController.navigate(Expensescreen) } ,
                viewModel = viewModel
            )
        }

        composable<Purchasedetailsscreen> {
            PurchaseDetailsScreen(
                purchaseData = purchaseData ?: return@composable,
                onClickBack = { navController.navigate(Purchasescreen) },
                onClickEdit = { navController.navigate(purchaseformwithdata)},
                viewModel = viewModel
            )
        }

        composable<StaffScreen> {
            EmployeeList(employees = employees,
                onIconClicked = {  bottomNavigation(it) },
                currnetScreen = currentScreen,
                onFABClicked = { navController.navigate(Staffformwithnull) },
                onArrowClicked = { employee = it
                    navController.navigate(Staffdetailsscreen) })
        }

        composable<Staffdetailsscreen> {
               EmployeeDetailsScreen(employee = employee?:return@composable,
                   onClickBack = { navController.navigate(StaffScreen) },
                   onClickEdit = {navController.navigate(Staffformwithdata)}
               )
        }

        composable<Staffformwithnull> {
            EmployeeForm(
                viewModel = viewModel,
                employee = employee,
                onClickBack = { navController.navigate(StaffScreen) }
            )
        }

        composable<Staffformwithdata> {
            EmployeeForm(
                viewModel = viewModel,
                employee = null,
                onClickBack = { navController.navigate(StaffScreen) }
            )
        }
        composable<Attendancescreen> {
            AttendanceEmployeeList(onBackClicked = {navController.navigate(Homescreen)},
                employees = employees,
                onLockClikeded = {},
                viewModel = viewModel,
                {viewModel.markAttendance()}
            )
        }

        composable<VendorScreen> {
            VendorsScreen(
                viewModel=viewModel,
                onClickBack = { navController.navigate(Homescreen) })
        }

        composable<BankCardScreen> {
            BankCardScreen(
                viewModel=viewModel,
                onClickBack = { navController.navigate(Homescreen) }
            )
        }

        composable<FestivalScreen> {
            FestivalApp(
                viewModel = viewModel,
                onClickBack = { navController.navigate(Homescreen) }
            )
        }
  }
}
