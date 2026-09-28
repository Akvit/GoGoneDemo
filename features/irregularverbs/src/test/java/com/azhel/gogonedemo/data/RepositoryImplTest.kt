package com.azhel.gogonedemo.data

import com.azhel.gogonedemo.data.dao.VerbsDao
import com.azhel.gogonedemo.data.entities.VerbWithTranslation
import com.azhel.gogonedemo.data.entities.VerbsStatisticEntity
import com.azhel.gogonedemo.data.entities.VerbsTable
import com.azhel.gogonedemo.domain.VerbsFrequencyEnum
import com.azhel.gogonedemo.domain.VerbsPatternEnum
import com.azhel.gogonedemo.domain.models.VerbModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RepositoryImplTest {

    private val dao = mockk<VerbsDao>()
    private val repository = RepositoryImpl(dao)

    private val locale = "en"

    private val verb = VerbsTable(
        id = 1,
        infinitive = "go",
        pastSimple = "went",
        pastParticipate = "gone",
        frequency = "high",
        pattern = "ABC",
        answerHistory = null,
        isLearning = true
    )

    private val verbWithTranslation = VerbWithTranslation(
        verb = verb,
        translation = "идти"
    )

    private val verbModel = VerbModel(
        id = 1,
        infinitive = "go",
        pastSimple = "went",
        pastParticipate = "gone",
        frequency = "high",
        pattern = "ABC",
        translation = "идти",
        answersGiven = null,
        rightAnswers = null,
        isLearning = true
    )

    @Test
    fun `getAllVerbs calls dao and maps entities to models`() = runTest {
        coEvery {
            dao.getAllVerbs(locale)
        } returns listOf(verbWithTranslation)

        val result = repository.getAllVerbs(locale)

        assertEquals(
            Result.success(listOf(verbModel)),
            result
        )

        coVerify(exactly = 1) {
            dao.getAllVerbs(locale)
        }

        confirmVerified(dao)
    }

    @Test
    fun `getVerbsByFrequency calls dao and maps entities to models`() =
        runTest {
            val frequency = VerbsFrequencyEnum.HIGH.str

            coEvery {
                dao.getVerbsByFrequency(locale, frequency)
            } returns listOf(verbWithTranslation)

            val result = repository.getVerbsByFrequency(
                locale,
                frequency
            )

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsByFrequency(locale, frequency)
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsByPattern calls dao and maps entities to models`() =
        runTest {
            val pattern = VerbsPatternEnum.ABC.str

            coEvery {
                dao.getVerbsByPattern(locale, pattern)
            } returns listOf(verbWithTranslation)

            val result = repository.getVerbsByPattern(
                locale,
                pattern
            )

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsByPattern(locale, pattern)
            }

            confirmVerified(dao)
        }

    @Test
    fun `getRandomVerbs calls dao and maps entities to models`() =
        runTest {
            val count = 20

            coEvery {
                dao.getRandomVerbs(locale, count)
            } returns listOf(verbWithTranslation)

            val result = repository.getRandomVerbs(
                locale,
                count
            )

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getRandomVerbs(locale, count)
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsUnderLearn calls dao and maps entities to models`() =
        runTest {
            coEvery {
                dao.getLearningVerbs(locale)
            } returns listOf(verbWithTranslation)

            val result = repository.getVerbsUnderLearn(locale)

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getLearningVerbs(locale)
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsUnderLearnNotTested calls dao and maps entities to models`() =
        runTest {
            coEvery {
                dao.getVerbsUnderLearnNotTested(locale)
            } returns listOf(verbWithTranslation)

            val result = repository.getVerbsUnderLearnNotTested(locale)

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsUnderLearnNotTested(locale)
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsUnderLearnByRate calls dao with rate range and maps entities`() =
        runTest {
            val minPercent = 0
            val maxPercent = 99

            coEvery {
                dao.getLearningVerbsByAnswerPercent(
                    locale,
                    minPercent,
                    maxPercent
                )
            } returns listOf(verbWithTranslation)

            val result = repository.getVerbsUnderLearnByRate(
                locale,
                minPercent,
                maxPercent
            )

            assertEquals(
                Result.success(listOf(verbModel)),
                result
            )

            coVerify(exactly = 1) {
                dao.getLearningVerbsByAnswerPercent(
                    locale,
                    minPercent,
                    maxPercent
                )
            }

            confirmVerified(dao)
        }

    @Test
    fun `toggleLearning calls dao with changes`() = runTest {
        val changes = mapOf(
            1 to true,
            2 to false,
            3 to true
        )

        coEvery {
            dao.updateLearningStatuses(changes)
        } just Runs

        val result = repository.toggleLearning(changes)

        assertEquals(
            Result.success(Unit),
            result
        )

        coVerify(exactly = 1) {
            dao.updateLearningStatuses(changes)
        }

        confirmVerified(dao)
    }

    @Test
    fun `getVerbsCount calls dao`() = runTest {
        coEvery {
            dao.getVerbsCount()
        } returns 100

        val result = repository.getVerbsCount()

        assertEquals(
            Result.success(100),
            result
        )

        coVerify(exactly = 1) {
            dao.getVerbsCount()
        }

        confirmVerified(dao)
    }

    @Test
    fun `getVerbsCountByFrequency calls dao`() = runTest {
        val frequency = VerbsFrequencyEnum.HIGH.str

        coEvery {
            dao.getVerbsCountByFrequency(frequency)
        } returns 50

        val result = repository.getVerbsCountByFrequency(frequency)

        assertEquals(
            Result.success(50),
            result
        )

        coVerify(exactly = 1) {
            dao.getVerbsCountByFrequency(frequency)
        }

        confirmVerified(dao)
    }

    @Test
    fun `getVerbsCountByPattern calls dao`() = runTest {
        val pattern = VerbsPatternEnum.ABC.str

        coEvery {
            dao.getVerbsCountByPattern(pattern)
        } returns 25

        val result = repository.getVerbsCountByPattern(pattern)

        assertEquals(
            Result.success(25),
            result
        )

        coVerify(exactly = 1) {
            dao.getVerbsCountByPattern(pattern)
        }

        confirmVerified(dao)
    }

    @Test
    fun `getVerbsCountByVerbsUnderLearnAll calls dao`() =
        runTest {
            coEvery {
                dao.getVerbsCountByVerbsUnderLearnAll()
            } returns 40

            val result =
                repository.getVerbsCountByVerbsUnderLearnAll()

            assertEquals(
                Result.success(40),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsCountByVerbsUnderLearnAll()
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsCountByVerbsUnderLearnNotTested calls dao`() =
        runTest {
            coEvery {
                dao.getVerbsCountByVerbsUnderLearnNotTested()
            } returns 15

            val result =
                repository.getVerbsCountByVerbsUnderLearnNotTested()

            assertEquals(
                Result.success(15),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsCountByVerbsUnderLearnNotTested()
            }

            confirmVerified(dao)
        }

    @Test
    fun `getVerbsCountByVerbsUnderLearnNeedPractice calls dao with rate range`() =
        runTest {
            val minPercent = 0
            val maxPercent = 99

            coEvery {
                dao.getVerbsCountByVerbsUnderLearnNeedPractice(
                    minPercent,
                    maxPercent
                )
            } returns 20

            val result =
                repository.getVerbsCountByVerbsUnderLearnNeedPractice(
                    minPercent,
                    maxPercent
                )

            assertEquals(
                Result.success(20),
                result
            )

            coVerify(exactly = 1) {
                dao.getVerbsCountByVerbsUnderLearnNeedPractice(
                    minPercent,
                    maxPercent
                )
            }

            confirmVerified(dao)
        }

    @Test
    fun `getAllVerbs returns failure when dao throws`() =
        runTest {
            val exception = RuntimeException("Database error")

            coEvery {
                dao.getAllVerbs(locale)
            } throws exception

            val result = repository.getAllVerbs(locale)

            assertEquals(
                Result.failure<List<VerbModel>>(exception),
                result
            )

            coVerify(exactly = 1) {
                dao.getAllVerbs(locale)
            }

            confirmVerified(dao)
        }

    @Test
    fun `toggleLearning returns failure when dao throws`() =
        runTest {
            val exception = RuntimeException("Database error")
            val changes = mapOf(1 to true)

            coEvery {
                dao.updateLearningStatuses(changes)
            } throws exception

            val result = repository.toggleLearning(changes)

            assertEquals(
                Result.failure<Unit>(exception),
                result
            )

            coVerify(exactly = 1) {
                dao.updateLearningStatuses(changes)
            }

            confirmVerified(dao)
        }

}