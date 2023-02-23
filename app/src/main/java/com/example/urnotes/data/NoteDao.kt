package com.example.urnotes.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("DELETE FROM notes_table WHERE id = :currId")
    suspend fun delete(currId: Int)

    @Query("SELECT * FROM notes_table ORDER BY date DESC, time DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE id = :currId")
    fun getNoteById(currId: Int): Flow<NoteEntity>

}