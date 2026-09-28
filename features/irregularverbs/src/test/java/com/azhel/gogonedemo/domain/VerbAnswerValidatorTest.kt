package com.azhel.gogonedemo.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class VerbAnswerValidatorTest {

    private val validator = VerbAnswerValidator()

    @ParameterizedTest
    @CsvSource(
        "go, go, true",
        "go, GO, true",
        "GO, go, true",
        "went, went, true",
        "was/were, was/were, true",
        "was/were, were/was, true",
        "was, was/were, false",
        "go, went, false",
        "go, ' go ', true",
        "go, '  GO  ', true",
        "to go, go, true",
        "togo, togo, true",
        "to    go, go, true",
        "togo, go, false",
        "' ', go, false"
    )
    fun `isCorrect returns expected result`(
        userAnswer: String,
        correctAnswer: String,
        expected: Boolean
    ) {
        assertEquals(
            expected,
            validator.isCorrect(userAnswer, correctAnswer)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'was/were', 'was, were'",
        "'was/were', 'was;were'",
        "'was/were', 'was were'",
        "'was,were', 'were/was'",
        "'was;were', 'was were'",
        "'  was / were  ', 'were / was'"
    )
    fun `isCorrect accepts different separators and order`(
        userAnswer: String,
        correctAnswer: String
    ) {
        assertTrue(
            validator.isCorrect(userAnswer, correctAnswer)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "go, gone",
        "was, were",
        "was/were, was",
        "go/went, go/gone",
        "one/two, one/three"
    )
    fun `isCorrect returns false for different answers`(
        userAnswer: String,
        correctAnswer: String
    ) {
        assertFalse(
            validator.isCorrect(userAnswer, correctAnswer)
        )
    }

    @Test
    fun `isCorrect ignores empty parts`() {
        assertTrue(
            validator.isCorrect(
                userAnswer = "was//were",
                correctAnswer = "was/were"
            )
        )
    }
}