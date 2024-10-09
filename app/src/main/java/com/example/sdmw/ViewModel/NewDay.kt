package com.example.sdmw.ViewModel

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

// Class to manage daily tasks
class DailyTaskManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("DailyTasks", Context.MODE_PRIVATE)

    // Function to perform the daily task
    fun performDailyTask() {
        val currentDate = getCurrentDate()
        val lastTaskDate = sharedPreferences.getString("lastTaskDate", "")

        // Check if the daily task has been performed today
        if (currentDate != lastTaskDate) {
            println("Performing daily task...")
            resetAttendanceFlag()

            val editor = sharedPreferences.edit()
            editor.putString("lastTaskDate", currentDate) // Fix the key name to match the date
            editor.apply()
        } else {
            println("Daily task already performed today.")
        }
    }

    // Helper function to reset the attendance flag
    private fun resetAttendanceFlag() {
        sharedPreferences.edit().putBoolean("attendance_marked", false).apply()
        println("Attendance flag reset for today.")
    }

    // Helper function to get the current date in "yyyy-MM-dd" format
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    // Function to check if attendance has been marked
    fun isAttendanceMarked(): Boolean {
        return sharedPreferences.getBoolean("attendance_marked", false)
    }

    // Function to mark attendance
    fun markAttendance() {
        sharedPreferences.edit().putBoolean("attendance_marked", true).apply()
        println("Attendance marked.")
    }
}
