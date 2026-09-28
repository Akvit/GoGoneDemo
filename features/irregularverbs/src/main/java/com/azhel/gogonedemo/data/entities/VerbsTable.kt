package com.azhel.gogonedemo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "verbs")
class VerbsTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val infinitive: String,
    val pastSimple: String,
    val pastParticipate: String,
    val frequency: String,
    val pattern: String,
    val answerHistory: String?,
    val isLearning: Boolean = false
)