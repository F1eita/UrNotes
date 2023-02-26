package com.example.urnotes

import com.example.urnotes.data.task_db.TaskEntity

data class Task(var isDone: Boolean, val text: String, var date: String, val id: Int)

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(this.isDone, this.text, this.date, this.id)
}