package com.example.urnotes

import com.example.urnotes.data.note_db.NoteEntity

data class Note(var title: String,
                var text: String, var time: String, var date: String,
                var id: Int)

fun Note.toNoteEntity(): NoteEntity{
    return NoteEntity(this.title, this.text, this.time, this.date, this.id)
}