package com.example.urnotes.data.task_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.urnotes.Task

@Entity(tableName = "tasks_table")
data class TaskEntity(val isDone: Boolean,
                      val text: String, val date: String,
                      @PrimaryKey(autoGenerate = true) var id: Int)

fun TaskEntity.toTask(): Task {
    return Task(this.isDone, this.text, this.date, this.id)
}