package com.azhel.gogonedemo.data

import com.azhel.gogonedemo.data.dao.VerbsDao
import com.azhel.gogonedemo.domain.Repository
import com.azhel.gogonedemo.domain.runSuspendCatching

class RepositoryImpl(private val dao: VerbsDao) : Repository {

    override suspend fun getAllVerbs(locale: String) = runSuspendCatching {
        dao.getAllVerbs(locale).map { it.toModel() }
    }

    override suspend fun getVerbsByFrequency(
        locale: String,
        frequency: String
    ) = runSuspendCatching {
        dao.getVerbsByFrequency(locale, frequency).map { it.toModel() }
    }

    override suspend fun getVerbsByPattern(
        locale: String,
        pattern: String
    ) = runSuspendCatching {
        dao.getVerbsByPattern(locale, pattern).map { it.toModel() }
    }

    override suspend fun getRandomVerbs(
        locale: String,
        count: Int
    ) = runSuspendCatching {
        dao.getRandomVerbs(locale, count).map { it.toModel() }
    }

    override suspend fun getVerbsUnderLearn(locale: String) = runSuspendCatching {
        dao.getLearningVerbs(locale).map { it.toModel() }
    }

    override suspend fun getVerbsUnderLearnNotTested(locale: String) = runSuspendCatching {
        dao.getVerbsUnderLearnNotTested(locale).map { it.toModel() }
    }

    override suspend fun getVerbsUnderLearnByRate(
        locale: String,
        minPercent: Int,
        maxPercent: Int
    ) = runSuspendCatching {
        dao.getLearningVerbsByAnswerPercent(locale, minPercent, maxPercent).map { it.toModel() }
    }

    override suspend fun toggleLearning(changes: Map<Int, Boolean>) = runSuspendCatching {
        dao.updateLearningStatuses(changes)
    }

    override suspend fun getVerbsCount() = runSuspendCatching {
        dao.getVerbsCount()
    }

    override suspend fun getVerbsCountByFrequency(frequency: String) = runSuspendCatching {
        dao.getVerbsCountByFrequency(frequency)
    }

    override suspend fun getVerbsCountByPattern(pattern: String) = runSuspendCatching {
        dao.getVerbsCountByPattern(pattern)
    }

    override suspend fun getVerbsCountByVerbsUnderLearnAll() = runSuspendCatching {
        dao.getVerbsCountByVerbsUnderLearnAll()
    }

    override suspend fun getVerbsCountByVerbsUnderLearnNotTested() = runSuspendCatching {
        dao.getVerbsCountByVerbsUnderLearnNotTested()
    }

    override suspend fun getVerbsCountByVerbsUnderLearnNeedPractice(
        minPercent: Int,
        maxPercent: Int
    ) = runSuspendCatching {
        dao.getVerbsCountByVerbsUnderLearnNeedPractice(minPercent, maxPercent)
    }
}
