package com.azhel.gogonedemo.domain

import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.domain.models.VerbStatisticModel

interface Repository {

    suspend fun getAllVerbs(locale: String): Result<List<VerbModel>>

    suspend fun getVerbsByFrequency(locale: String, frequency: String): Result<List<VerbModel>>

    suspend fun getVerbsByPattern(locale: String, pattern: String): Result<List<VerbModel>>

    suspend fun getRandomVerbs(locale: String, count: Int): Result<List<VerbModel>>

    suspend fun getVerbsUnderLearn(locale: String): Result<List<VerbModel>>

    suspend fun getVerbsUnderLearnNotTested(locale: String): Result<List<VerbModel>>

    suspend fun getVerbsUnderLearnByRate(locale: String, minPercent: Int, maxPercent: Int): Result<List<VerbModel>>

    suspend fun toggleLearning(changes: Map<Int, Boolean>): Result<Unit>

    suspend fun getVerbsCount(): Result<Int>

    suspend fun getVerbsCountByFrequency(frequency: String): Result<Int>

    suspend fun getVerbsCountByPattern(pattern: String): Result<Int>

    suspend fun getVerbsCountByVerbsUnderLearnAll(): Result<Int>

    suspend fun getVerbsCountByVerbsUnderLearnNotTested(): Result<Int>

    suspend fun getVerbsCountByVerbsUnderLearnNeedPractice(minPercent: Int, maxPercent: Int): Result<Int>

}
