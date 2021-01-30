package com.husam.to_do.cruds

import android.util.Log
import com.husam.to_do.data.Task
import com.husam.to_do.providers.DatabaseProvider
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class Task {

    private lateinit var realm: Realm
    private var debug = true

    fun add(task: Task) {
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
                        "id:" + it.id + "Task:" + it.content + " Priority:" + it.priority + " Created:" +
                                it.created + " migrateDate:" + it.migrateDate + " hide:" +
                                it.hide + " deleted:" + it.deleted
                    )
                }
                Log.d("NEW_TASK", "========== END =========")
            }
        }
    }
}