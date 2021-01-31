package com.husam.to_do.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.husam.to_do.data.Task
import com.husam.to_do.providers.DatabaseProvider
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskContent = MutableLiveData<String>()
    private val taskStatus = MutableLiveData<String>()
    private val taskPriority = MutableLiveData<String>()
    val noTasks: MutableLiveData<Boolean> = MutableLiveData(true)

    private var realm: Realm = DatabaseProvider().getDatabaseInstance()
    var getAllData: RealmResults<Task>? = realm.where<Task>().findAll()

    fun databaseIsEmpty(list: List<Task>) {
        noTasks.value = list.isEmpty()
    }

    fun getAllData(): RealmResults<Task>? {
        val data = realm.where<Task>().findAll()
        this.getAllData = data
        return data
    }

    fun sendTask(task: String, priority: String) {
        taskContent.value = task
        taskPriority.value = priority.toUpperCase(Locale.ROOT)
    }

    fun getContent(): String {
        return taskContent.value.toString()
    }

    fun getPriority(): String {
        return taskPriority.value.toString()
    }

    fun getStatus(): String {
        return taskStatus.value.toString()
    }

    fun deleteAll(): Boolean {
        try {
            // Delete all tasks
            realm.executeTransaction { realm ->
                realm.deleteAll()
            }
        } catch (e: Exception) {
            Log.e("REALM_ERR", "ERROR: " + e.message)
            realm.cancelTransaction()
            return false
        } finally {
            return true
        }
    }


}