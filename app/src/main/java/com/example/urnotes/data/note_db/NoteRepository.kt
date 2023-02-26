package com.example.urnotes.data.note_db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<NoteEntity>> = noteDao.getNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }
    suspend fun update(note: NoteEntity){
        noteDao.update(note)
    }
    suspend fun delete(id: Int){
        noteDao.delete(id)
    }
    fun getNoteById(currId: Int): Flow<NoteEntity>{
        return noteDao.getNoteById(currId)
    }

}