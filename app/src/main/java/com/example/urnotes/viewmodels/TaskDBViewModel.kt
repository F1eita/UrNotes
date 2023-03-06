package com.example.urnotes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.urnotes.Repositories
import com.example.urnotes.data.task_db.TaskEntity
import kotlinx.coroutines.launch

class TaskDBViewModel: ViewModel()  {
    //for database
    val tasks = Repositories.taskRepository.allTasks.asLiveData()
    fun insert(taskEntity: TaskEntity) = viewModelScope.launch{
        Repositories.taskRepository.insert(taskEntity)
    }
    fun update(taskEntity: TaskEntity) = viewModelScope.launch{
        Repositories.taskRepository.update(taskEntity)
    }
    fun delete(currId: Int) = viewModelScope.launch {
        Repositories.taskRepository.delete(currId)
    }
}