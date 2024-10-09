package com.example.sdmw.Views

import androidx.compose.runtime.MutableState

fun validateFormData(formData: List<String>, showAlert: MutableState<Boolean>): Boolean {
    for (data in formData) {
        if (data.isEmpty() || data == "null" || data == "-1") {
            showAlert.value = true
            return false
        }
    }
    return true
}
