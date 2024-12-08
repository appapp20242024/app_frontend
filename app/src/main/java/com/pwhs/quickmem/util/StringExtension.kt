package com.pwhs.quickmem.util

import androidx.compose.ui.graphics.Color
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.upperCaseFirstLetter(): String {
    return this.replaceFirstChar { it.uppercase() }
}

fun String.getUsernameFromEmail(): String {
    return if (this.length < 4) {
        "user${(1000..9999).random()}"
    } else {
        this.substringBefore("@")
    }
}

fun String.getNameFromEmail(): String {
    return this.substringBefore("@").replace(".", " ")
}

fun String.emailIsValid(): Boolean {
    return this.validEmail()
}

fun String.strongPassword(): Boolean {
    val regex = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$".toRegex()
    return this.matches(regex)
}

fun String.toTimestamp(): Long? {
    if (this.isEmpty()) return null
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(this) ?: return 0
    return date.time
}

fun String.toColor(): Color {
    val color = Color(android.graphics.Color.parseColor(this))
    return color
}

fun formatDate(dateString: String): String {
    if (dateString.isEmpty()) {
        return ""
    }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(dateString) ?: return ""
    return outputFormat.format(date)
}

fun String.calculateTimeAgo(): String {
    return try {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val createdDateTime = OffsetDateTime.parse(this, formatter)
        val now = OffsetDateTime.now()
        val duration = Duration.between(createdDateTime, now)

        when {
            duration.toMinutes() < 1 -> "${duration.seconds}s"
            duration.toHours() < 1 -> "${duration.toMinutes()}m"
            duration.toDays() < 1 -> "${duration.toHours()}h"
            else -> "${duration.toDays()}d"
        }
    } catch (e: Exception) {
        "N/A"
    }
}

fun String.toLocalDateTime(): LocalDateTime? {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime.parse(this, formatter)
    } catch (e: Exception) {
        null
    }
}