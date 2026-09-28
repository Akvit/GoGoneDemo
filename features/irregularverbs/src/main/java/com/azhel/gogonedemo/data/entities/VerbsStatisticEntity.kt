package com.azhel.gogonedemo.data.entities

data class VerbsStatisticEntity(
    val id: Int,
    val infinitive: String,
    val translation: String,
    val answerHistory: String,
    val isLearning: Boolean
)