package com.azhel.gogonedemo.domain.models

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerializable

@Serializable
data class VerbModel(
    val id: Int,
    val infinitive: String,
    val pastSimple: String,
    val pastParticipate: String,
    val frequency: String,
    val pattern: String,
    val translation: String,
    val answersGiven: Int?,
    val rightAnswers: Int?,
    val isLearning: Boolean
) : JavaSerializable