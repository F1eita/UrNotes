package com.example.urnotes.data.task_db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Query("DELETE FROM tasks_table WHERE id = :currId")
    suspend fun delete(currId: Int)

    @Query("SELECT * FROM tasks_table ORDER BY date")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks_table WHERE id = :currId")
    fun getTaskById(currId: Int): Flow<TaskEntity>
}