package com.azhel.gogonedemo.domain.usecases

import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.PERFECT_RESULT_PERCENT
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.Repository
import com.azhel.gogonedemo.domain.VerbsFrequencyEnum
import com.azhel.gogonedemo.domain.VerbsPatternEnum
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class GetVerbsCountUseCaseTest {

    private val repository = mockk<Repository>()
    private val useCase = GetVerbsCountUseCase(repository)

    @Test
    fun `execute with All calls getVerbsCount`() = runTest {
        coEvery {
            repository.getVerbsCount()
        } returns Result.success(100)

        val result = useCase.execute(ProcessSetup.All)

        Assertions.assertEquals(Result.success(100), result)

        coVerify(exactly = 1) {
            repository.getVerbsCount()
        }

        confirmVerified(repository)
    }

    @ParameterizedTest
    @EnumSource(VerbsFrequencyEnum::class)
    fun `execute with Frequency calls getVerbsCountByFrequency`(
        frequency: VerbsFrequencyEnum
    ) = runTest {
        coEvery {
            repository.getVerbsCountByFrequency(frequency.str)
        } returns Result.success(50)

        val result = useCase.execute(
            ProcessSetup.Frequency(frequency)
        )

        Assertions.assertEquals(Result.success(50), result)

        coVerify(exactly = 1) {
            repository.getVerbsCountByFrequency(frequency.str)
        }

        confirmVerified(repository)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 50])
    fun `execute with Number returns requested number without calling repository`(
        numberOfVerbs: Int
    ) = runTest {
        val result = useCase.execute(
            ProcessSetup.Number(numberOfVerbs)
        )

        Assertions.assertEquals(Result.success(numberOfVerbs), result)

        confirmVerified(repository)
    }

    @ParameterizedTest
    @EnumSource(VerbsPatternEnum::class)
    fun `execute with Pattern calls getVerbsCountByPattern`(
        pattern: VerbsPatternEnum
    ) = runTest {
        coEvery {
            repository.getVerbsCountByPattern(pattern.str)
        } returns Result.success(30)

        val result = useCase.execute(
            ProcessSetup.Pattern(pattern)
        )

        Assertions.assertEquals(Result.success(30), result)

        coVerify(exactly = 1) {
            repository.getVerbsCountByPattern(pattern.str)
        }

        confirmVerified(repository)
    }

    @Test
    fun `execute with VerbsUnderLearn ALL calls getVerbsCountByVerbsUnderLearnAll`() =
        runTest {
            coEvery {
                repository.getVerbsCountByVerbsUnderLearnAll()
            } returns Result.success(40)

            val result = useCase.execute(
                ProcessSetup.VerbsUnderLearn(
                    LearningVerbsFilter.ALL
                )
            )

            Assertions.assertEquals(Result.success(40), result)

            coVerify(exactly = 1) {
                repository.getVerbsCountByVerbsUnderLearnAll()
            }

            confirmVerified(repository)
        }

    @Test
    fun `execute with VerbsUnderLearn NOT_TESTED calls getVerbsCountByVerbsUnderLearnNotTested`() =
        runTest {
            coEvery {
                repository.getVerbsCountByVerbsUnderLearnNotTested()
            } returns Result.success(15)

            val result = useCase.execute(
                ProcessSetup.VerbsUnderLearn(
                    LearningVerbsFilter.NOT_TESTED
                )
            )

            Assertions.assertEquals(Result.success(15), result)

            coVerify(exactly = 1) {
                repository.getVerbsCountByVerbsUnderLearnNotTested()
            }

            confirmVerified(repository)
        }

    @Test
    fun `execute with VerbsUnderLearn NEED_PRACTICE calls getVerbsCountByVerbsUnderLearnNeedPractice`() =
        runTest {
            coEvery {
                repository.getVerbsCountByVerbsUnderLearnNeedPractice(0, 99)
            } returns Result.success(25)

            val result = useCase.execute(
                ProcessSetup.VerbsUnderLearn(
                    LearningVerbsFilter.NEED_PRACTICE
                )
            )

            Assertions.assertEquals(Result.success(25), result)

            coVerify(exactly = 1) {
                repository.getVerbsCountByVerbsUnderLearnNeedPractice(0, 99)
            }

            confirmVerified(repository)
        }

    @Test
    fun `execute with VerbsUnderLearn LEARNED calls getVerbsCountByVerbsUnderLearnNeedPractice`() =
        runTest {
            coEvery {
                repository.getVerbsCountByVerbsUnderLearnNeedPractice(
                    PERFECT_RESULT_PERCENT,
                    PERFECT_RESULT_PERCENT
                )
            } returns Result.success(10)

            val result = useCase.execute(
                ProcessSetup.VerbsUnderLearn(
                    LearningVerbsFilter.LEARNED
                )
            )

            Assertions.assertEquals(Result.success(10), result)

            coVerify(exactly = 1) {
                repository.getVerbsCountByVerbsUnderLearnNeedPractice(
                    PERFECT_RESULT_PERCENT,
                    PERFECT_RESULT_PERCENT
                )
            }

            confirmVerified(repository)
        }

}