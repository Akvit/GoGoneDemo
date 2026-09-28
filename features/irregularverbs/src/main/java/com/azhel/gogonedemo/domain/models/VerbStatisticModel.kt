package com.azhel.gogonedemo.domain.models

data class VerbStatisticModel(
    val verbId: Int,
    val infinitive: String,
    val translation: String,
    val answersGiven: Int,
    val rightAnswers: Int,
    val isUnderLearn: Boolean
) {
    val rightAnswersPercent: Int = ((rightAnswers.toFloat() / answersGiven.toFloat()) * 100).toInt()
}