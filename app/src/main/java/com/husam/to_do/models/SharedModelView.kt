package com.husam.to_do.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.husam.to_do.R
import com.husam.to_do.data.Priority
import com.husam.to_do.data.Status
import com.husam.to_do.data.Task
import com.husam.to_do.providers.DatabaseProvider
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class SharedModelView : ViewModel() {

    private lateinit var realm: Realm
    private var debug = true

    fun addTask(task: Task) {
        try {
            realm = DatabaseProvider().getDatabaseInstance()
            // All writes must be wrapped in a transaction to facilitate safe multi threading
            val maxId = realm.where<Task>().max("id")?.toInt()
            realm.executeTransaction { realm ->
                // Add a task
                val newTask = realm.createObject<Task>((maxId ?: 0) + 1)
                newTask.content = task.content
                newTask.priority = task.priority
            }
        } catch (e: Exception) {
            Log.d("ADD_TASK", "Realm Fail: " + e.message)
        } finally {
            if (debug) {
                val tasks = realm.where<Task>().findAll()

                Log.d("NEW_TASK", "========== START =========")
                tasks.forEach {
                    Log.d(
                        "NEW_TASK",
                        "id:" + it.id + "Task:" + it.content + " Priority:" + it.priority + " Status:"
                                + it.status + " Created:" + it.created + " migrateDate:"
                                + it.migrateDate + " hide:" + it.hide + " deleted:" + it.deleted
                    )
                }
                Log.d("NEW_TASK", "========== END =========")
            }
        }
    }

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
        return when (status.toUpperCase(Locale.ROOT)) {
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