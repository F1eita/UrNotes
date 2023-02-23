package com.example.urnotes

import android.content.Context
import androidx.room.Room
import com.example.urnotes.data.NoteDataBase
import com.example.urnotes.data.NoteRepository
object Repositories {
    private lateinit var applicationContext: Context

    private val database: NoteDataBase by lazy<NoteDataBase>{
        Room.databaseBuilder(applicationContext, NoteDataBase::class.java, "database.dp")
            .build()
    }

    val noteRepository: NoteRepository by lazy{
        NoteRepository(database.getNotesDao())
    }

    fun init(context: Context){
        applicationContext = context
    }
}