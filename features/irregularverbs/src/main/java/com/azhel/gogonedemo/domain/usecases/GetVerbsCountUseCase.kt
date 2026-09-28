package com.azhel.gogonedemo.domain.usecases

import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.PERFECT_RESULT_PERCENT
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.Repository

class GetVerbsCountUseCase(private val repository: Repository) {

    suspend fun execute(processSetup: ProcessSetup): Result<Int> {
        return when (processSetup) {
            ProcessSetup.All -> repository.getVerbsCount()
            is ProcessSetup.Frequency -> repository.getVerbsCountByFrequency(processSetup.verbsFrequencyEnum.str)
            is ProcessSetup.Number -> Result.success(processSetup.numberOfVerbs)
            is ProcessSetup.Pattern -> repository.getVerbsCountByPattern(processSetup.verbsPatternEnum.str)
            is ProcessSetup.VerbsUnderLearn -> handleVerbsUnderLearn(processSetup.progressPrecent)
        }
    }

    private suspend fun handleVerbsUnderLearn(progressPercent: LearningVerbsFilter): Result<Int> = when (progressPercent) {
        LearningVerbsFilter.ALL -> repository.getVerbsCountByVerbsUnderLearnAll()
        LearningVerbsFilter.NOT_TESTED -> repository.getVerbsCountByVerbsUnderLearnNotTested()
        LearningVerbsFilter.NEED_PRACTICE -> repository.getVerbsCountByVerbsUnderLearnNeedPractice(0, 99)
        LearningVerbsFilter.LEARNED -> repository.getVerbsCountByVerbsUnderLearnNeedPractice(PERFECT_RESULT_PERCENT, PERFECT_RESULT_PERCENT)
    }
}
