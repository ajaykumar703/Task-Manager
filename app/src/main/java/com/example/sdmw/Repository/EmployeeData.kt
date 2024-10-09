package com.example.sdmw.Repository

import android.content.Context

class EmployeeRepository(context: Context) {

    private val employeeDao = AppDatabase.getDatabase(context).employeeDao()

    suspend fun getAllEmployees(): List<Employee> {
        return employeeDao.getAllEmployees()
    }

    suspend fun upsertEmployee(employee: Employee) {
        employeeDao.upsertEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }
}
