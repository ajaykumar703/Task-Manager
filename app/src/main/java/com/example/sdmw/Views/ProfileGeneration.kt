package com.example.sdmw.Views


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.ui.graphics.toArgb

fun generateInitialsImage(name: String, width: Int = 300, height: Int = 300, iniColor: androidx.compose.ui.graphics.Color? = null): Bitmap {
    // Split the name into parts and get the first letters
    val initials = name.split(" ")
        .mapNotNull { it.firstOrNull()?.toString()?.uppercase() }
        .take(2) // Take the first 2 letters
        .joinToString("")

    // List of cool background colors
    val coolColors = listOf(
        Color.rgb(63, 81, 181),   // Deep blue
        Color.rgb(33, 150, 243),  // Light blue
        Color.rgb(76, 175, 80),   // Green
        Color.rgb(255, 193, 7),   // Amber
        Color.rgb(244, 67, 54),   // Red
        Color.rgb(156, 39, 176),  // Purple
        Color.rgb(0, 188, 212)    // Cyan
    )

    // Generate a hash of the name to consistently pick a color
    val colorIndex = name.length % coolColors.size
    val stableColor = coolColors[colorIndex]

    // Create a Bitmap
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Define the stable background color
    val backgroundPaint = Paint().apply {
        color = iniColor?.toArgb() ?: stableColor
        style = Paint.Style.FILL
    }

    // Draw background
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)

    // Define text (initials) paint style
    val textPaint = Paint().apply {
        color = Color.WHITE // Text color
        textSize = width / 2.5f // Adjust text size relative to image size
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }

    // Calculate position for initials to be centered
    val xPos = width / 2f
    val yPos = (canvas.height / 2f) - ((textPaint.descent() + textPaint.ascent()) / 2)

    // Draw initials
    canvas.drawText(initials, xPos, yPos, textPaint)

    return bitmap
}
