package com.example.urnotes.data.note_db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    version = 2,
    autoMigrations = [
      AutoMigration(from = 1, to = 2, spec = NoteDataBase.Migration1To2::class)
    ],
    entities = [NoteEntity::class]
)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun getNotesDao(): NoteDao

    @DeleteColumn(tableName = "notes_table", columnName = "category")
    class Migration1To2: AutoMigrationSpec
}