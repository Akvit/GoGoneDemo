package com.azhel.gogonedemo.domain.usecases

import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.PERFECT_RESULT_PERCENT
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.Repository
import com.azhel.gogonedemo.domain.getSupportedLocale
import com.azhel.gogonedemo.domain.models.VerbModel

class GetVerbsUseCase(private val repository: Repository) {

    suspend fun execute(locale: String, processSetup: ProcessSetup): Result<List<VerbModel>> {
        val supportedLocale = getSupportedLocale(locale)

        return when (processSetup) {
            ProcessSetup.All -> repository.getAllVerbs(supportedLocale)
            is ProcessSetup.Frequency -> repository.getVerbsByFrequency(supportedLocale, processSetup.verbsFrequencyEnum.str)
            is ProcessSetup.Number -> repository.getRandomVerbs(supportedLocale, processSetup.numberOfVerbs)
            is ProcessSetup.Pattern -> repository.getVerbsByPattern(supportedLocale, processSetup.verbsPatternEnum.str)
            is ProcessSetup.VerbsUnderLearn -> when (processSetup.progressPrecent) {
                LearningVerbsFilter.ALL -> repository.getVerbsUnderLearn(supportedLocale)
                LearningVerbsFilter.NOT_TESTED -> repository.getVerbsUnderLearnNotTested(supportedLocale)
                LearningVerbsFilter.NEED_PRACTICE -> repository.getVerbsUnderLearnByRate(supportedLocale, 0, 99)
                LearningVerbsFilter.LEARNED -> repository.getVerbsUnderLearnByRate(supportedLocale, PERFECT_RESULT_PERCENT, PERFECT_RESULT_PERCENT)
            }
        }
    }
}
