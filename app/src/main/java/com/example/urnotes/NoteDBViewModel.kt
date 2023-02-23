package com.example.urnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.urnotes.data.NoteEntity
import kotlinx.coroutines.launch

class NoteDBViewModel: ViewModel() {
    //for database
    val notes = Repositories.noteRepository.allNotes.asLiveData()
    fun getNoteById(currId: Int): LiveData<NoteEntity> {
        return Repositories.noteRepository.getNoteById(currId).asLiveData()
    }
    fun insert(noteEntity: NoteEntity) = viewModelScope.launch{
        Repositories.noteRepository.insert(noteEntity)
    }
    fun update(noteEntity: NoteEntity) = viewModelScope.launch{
        Repositories.noteRepository.update(noteEntity)
    }
    fun delete(currId: Int) = viewModelScope.launch {
        Repositories.noteRepository.delete(currId)
    }
}