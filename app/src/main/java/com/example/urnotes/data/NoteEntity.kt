package com.example.urnotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(tableName = "notes_table")
data class NoteEntity(val title: String,
                      val text: String, val time: String, val date: String,
                      @PrimaryKey(autoGenerate = true) var id: Int)
