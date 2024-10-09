package com.example.sdmw.Views

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun DateFormatter(selectedDate:Long?): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = selectedDate?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(formatter)
    } ?: ""
    return formattedDate

}