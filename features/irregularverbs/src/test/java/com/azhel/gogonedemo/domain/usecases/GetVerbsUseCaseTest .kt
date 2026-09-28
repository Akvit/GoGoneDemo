package com.azhel.gogonedemo.domain.usecases

import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.PERFECT_RESULT_PERCENT
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.Repository
import com.azhel.gogonedemo.domain.VerbsFrequencyEnum
import com.azhel.gogonedemo.domain.VerbsPatternEnum
import com.azhel.gogonedemo.domain.models.VerbModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class GetVerbsUseCaseTest {

    private val repository = mockk<Repository>()
    private val useCase = GetVerbsUseCase(repository)

    private val locale = "en"

    private val verbs = listOf(
        VerbModel(
            id = 1,
            infinitive = "go",
            pastSimple = "went",
            pastParticipate = "gone",
            frequency = "high",
            pattern = "ABC",
            translation = "идти",
            answersGiven = null,
            rightAnswers = null,
            isLearning = false
        )
    )

    @Test
    fun `execute with All calls getAllVerbs`() = runTest {
        coEvery {
            repository.getAllVerbs(locale)
        } returns Result.success(verbs)

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.All
        )

        assertEquals(Result.success(verbs), result)

        coVerify(exactly = 1) {
            repository.getAllVerbs(locale)
        }

        confirmVerified(repository)
    }

    @Test
    fun `execute with Number calls getRandomVerbs`() = runTest {
        val numberOfVerbs = 20

        coEvery {
            repository.getRandomVerbs(locale, numberOfVerbs)
        } returns Result.success(verbs)

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.Number(numberOfVerbs)
        )

        assertEquals(Result.success(verbs), result)

        coVerify(exactly = 1) {
            repository.getRandomVerbs(locale, numberOfVerbs)
        }

        confirmVerified(repository)
    }

    @ParameterizedTest
    @EnumSource(VerbsFrequencyEnum::class)
    fun `execute with Frequency calls getVerbsByFrequency`(
        frequency: VerbsFrequencyEnum
    ) = runTest {
        coEvery {
            repository.getVerbsByFrequency(locale, frequency.str)
        } returns Result.success(verbs)

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.Frequency(frequency)
        )

        assertEquals(Result.success(verbs), result)

        coVerify(exactly = 1) {
            repository.getVerbsByFrequency(locale, frequency.str)
        }

        confirmVerified(repository)
    }

    @ParameterizedTest
    @EnumSource(VerbsPatternEnum::class)
    fun `execute with Pattern calls getVerbsByPattern`(
        pattern: VerbsPatternEnum
    ) = runTest {
        coEvery {
            repository.getVerbsByPattern(locale, pattern.str)
        } returns Result.success(verbs)

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.Pattern(pattern)
        )

        assertEquals(Result.success(verbs), result)

        coVerify(exactly = 1) {
            repository.getVerbsByPattern(locale, pattern.str)
        }

        confirmVerified(repository)
    }

    @ParameterizedTest
    @EnumSource(LearningVerbsFilter::class)
    fun `execute with VerbsUnderLearn calls correct repository method`(
        filter: LearningVerbsFilter
    ) = runTest {
        when (filter) {
            LearningVerbsFilter.ALL -> {
                coEvery {
                    repository.getVerbsUnderLearn(locale)
                } returns Result.success(verbs)
            }

            LearningVerbsFilter.NOT_TESTED -> {
                coEvery {
                    repository.getVerbsUnderLearnNotTested(locale)
                } returns Result.success(verbs)
            }

            LearningVerbsFilter.NEED_PRACTICE -> {
                coEvery {
                    repository.getVerbsUnderLearnByRate(locale, 0, 99)
                } returns Result.success(verbs)
            }

            LearningVerbsFilter.LEARNED -> {
                coEvery {
                    repository.getVerbsUnderLearnByRate(
                        locale,
                        PERFECT_RESULT_PERCENT,
                        PERFECT_RESULT_PERCENT
                    )
                } returns Result.success(verbs)
            }
        }

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.VerbsUnderLearn(filter)
        )

        assertEquals(Result.success(verbs), result)

        when (filter) {
            LearningVerbsFilter.ALL -> {
                coVerify(exactly = 1) {
                    repository.getVerbsUnderLearn(locale)
                }
            }

            LearningVerbsFilter.NOT_TESTED -> {
                coVerify(exactly = 1) {
                    repository.getVerbsUnderLearnNotTested(locale)
                }
            }

            LearningVerbsFilter.NEED_PRACTICE -> {
                coVerify(exactly = 1) {
                    repository.getVerbsUnderLearnByRate(locale, 0, 99)
                }
            }

            LearningVerbsFilter.LEARNED -> {
                coVerify(exactly = 1) {
                    repository.getVerbsUnderLearnByRate(
                        locale,
                        PERFECT_RESULT_PERCENT,
                        PERFECT_RESULT_PERCENT
                    )
                }
            }
        }

        confirmVerified(repository)
    }

    @Test
    fun `execute returns failure when repository fails`() = runTest {
        val exception = RuntimeException("Database error")

        coEvery {
            repository.getAllVerbs(locale)
        } returns Result.failure(exception)

        val result = useCase.execute(
            locale = locale,
            processSetup = ProcessSetup.All
        )

        assertEquals(
            Result.failure<List<VerbModel>>(exception),
            result
        )

        coVerify(exactly = 1) {
            repository.getAllVerbs(locale)
        }

        confirmVerified(repository)
    }
}