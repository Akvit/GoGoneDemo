package com.azhel.gogonedemo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azhel.gogonedemo.data.dao.VerbsDao
import com.azhel.gogonedemo.data.entities.VerbTranslationsTable
import com.azhel.gogonedemo.data.entities.VerbsTable

@Database(
    entities = [VerbsTable::class, VerbTranslationsTable::class],
    version = 1,
    exportSchema = false
)
abstract class IrregularVerbsDataBase : RoomDatabase() {

    abstract fun verbsDao(): VerbsDao

}