package com.example.urnotes

import android.content.Context
import androidx.room.Room
import com.example.urnotes.data.note_db.NoteDataBase
import com.example.urnotes.data.note_db.NoteRepository
import com.example.urnotes.data.task_db.TaskDataBase
import com.example.urnotes.data.task_db.TaskRepository

object Repositories {
    private lateinit var applicationContext: Context

    private val database: NoteDataBase by lazy<NoteDataBase>{
        Room.databaseBuilder(applicationContext, NoteDataBase::class.java, "database.dp")
            .build()
    }

    val noteRepository: NoteRepository by lazy{
        NoteRepository(database.getNotesDao())
    }

    private val taskDatabase: TaskDataBase by lazy<TaskDataBase>{
        Room.databaseBuilder(applicationContext, TaskDataBase::class.java, "taskdatabase.dp")
            .build()
    }

    val taskRepository: TaskRepository by lazy{
        TaskRepository(taskDatabase.getTasksDao())
    }

    fun init(context: Context){
        applicationContext = context
    }
}