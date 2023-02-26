package com.example.urnotes.data.task_db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [TaskEntity::class]
)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun getTasksDao(): TaskDao
}