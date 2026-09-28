package com.azhel.gogonedemo.data

object AnswersHandler {

    fun updateMask(answerHistory: String?, isCorrect: Boolean): String {
        val answer = if (isCorrect) RIGHT_ANSWER else WRONG_ANSWER

        return buildString {
            append(answerHistory.orEmpty())
            append(answer)
        }.takeLast(HISTORY_LENGTH_LIMIT)
    }

    fun getAnswersCount(answerHistory: String?) = answerHistory?.length

    fun getRightAnswers(answerHistory: String?) = answerHistory?.count { it == RIGHT_ANSWER }

    private const val RIGHT_ANSWER = '1'
    private const val WRONG_ANSWER = '0'
    const val HISTORY_LENGTH_LIMIT = 4
}
