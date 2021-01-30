package com.husam.to_do.models

import androidx.lifecycle.ViewModel
import com.husam.to_do.R
import com.husam.to_do.data.Priority
import com.husam.to_do.data.Status
import java.util.*

class SharedModelView : ViewModel() {


    fun parseContent(content: String): String {
        return if (content.isNotEmpty()) content
        else "-- Task Missing --"
    }

    fun parsePriority(priority: String): String {
        return when (priority.toUpperCase(Locale.ROOT)) {
            "HIGH" -> Priority.HIGH.toString()
            "MEDIUM" -> Priority.MEDIUM.toString()
            else -> Priority.LOW.toString()
        }
    }

    fun parseStatus(status: String): String {
        return when (status) {
            "ACTIVE" -> Status.ACTIVE.toString()
            else -> Status.DONE.toString()
        }
    }

    fun getPriorityColor(priority: String): Int {
        return when (priority) {
            Priority.HIGH.toString() -> R.color.high
            Priority.MEDIUM.toString() -> R.color.medium
            else -> R.color.low
        }
    }

}