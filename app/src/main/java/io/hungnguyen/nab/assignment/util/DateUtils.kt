package io.hungnguyen.nab.assignment.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class TimeFormat(val format: String) {
    DEFAULT("EEE, dd MMM yyyy")
}

fun timeStampToFormattedDate(
    timeStamp: Long,
    format: TimeFormat = TimeFormat.DEFAULT
): String {
    return try {
        val sdf = SimpleDateFormat(format.format, Locale.getDefault())
        val netDate = Date(timeStamp * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        ""
    }
}