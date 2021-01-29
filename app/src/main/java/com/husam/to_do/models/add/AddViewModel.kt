package com.husam.to_do.models.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    private val taskContent = MutableLiveData<String>()
    val taskPriority = MutableLiveData<String>()

    fun sendTask(task: String, priority: String) {
        taskContent.value = task
        taskPriority.value = priority
    }

    fun getTaskContent(): String {
        return taskContent.value.toString()
    }

    fun getTaskPriority(): String {
        return taskPriority.value.toString()
    }
}