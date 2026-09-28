package com.azhel.gogonedemo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translations")
class VerbTranslationsTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val verbId: Int,
    val translation: String,
    val locale: String
)
