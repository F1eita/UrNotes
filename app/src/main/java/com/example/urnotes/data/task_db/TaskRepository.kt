package com.example.urnotes.data.task_db

import androidx.annotation.WorkerThread
import com.example.urnotes.data.task_db.TaskDao
import com.example.urnotes.data.task_db.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){
    val allTasks: Flow<List<TaskEntity>> = taskDao.getTasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: TaskEntity) {
        taskDao.insert(task)
    }
    suspend fun update(task: TaskEntity){
        taskDao.update(task)
    }
    suspend fun delete(id: Int){
        taskDao.delete(id)
    }
}