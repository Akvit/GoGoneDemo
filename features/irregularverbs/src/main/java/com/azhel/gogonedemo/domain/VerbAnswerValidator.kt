package com.azhel.gogonedemo.domain

class VerbAnswerValidator {

    fun isCorrect(
        userAnswer: String,
        correctAnswer: String
    ): Boolean {
        return normalize(userAnswer) == normalize(correctAnswer)
    }

    private fun normalize(value: String): Set<String> {
        return value
            .lowercase()
            .trim()
            .split(Regex("[/;,\\s]+"))
            .dropWhile { it == "to" }
            .toSet()
    }

}