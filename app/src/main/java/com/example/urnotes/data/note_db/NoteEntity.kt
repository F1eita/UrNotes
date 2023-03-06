package com.example.urnotes.data.note_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.urnotes.Note
import java.sql.Date
import java.sql.Time

@Entity(tableName = "notes_table")
data class NoteEntity(val title: String,
                      val text: String, val time: String, val date: String,
                      @PrimaryKey(autoGenerate = true) var id: Int)

fun NoteEntity.toNote(): Note{
    return Note(this.title, this.text, this.time, this.date, this.id)
}