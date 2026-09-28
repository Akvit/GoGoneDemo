package com.azhel.gogonedemo.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.azhel.gogonedemo.data.entities.VerbWithTranslation
import com.azhel.gogonedemo.data.entities.VerbsStatisticEntity
import com.azhel.gogonedemo.domain.models.VerbModel

@Dao
interface VerbsDao {

    @Query("""
        SELECT v.*, t.translation 
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        ORDER BY v.id
    """)
    suspend fun getAllVerbs(locale: String): List<VerbWithTranslation>

    @Query("""
        SELECT v.*, t.translation 
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        WHERE v.frequency = :frequency
        ORDER BY v.id
    """)
    suspend fun getVerbsByFrequency(locale: String, frequency: String): List<VerbWithTranslation>

    @Query("""
        SELECT v.*, t.translation 
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        WHERE v.pattern = :pattern
        ORDER BY v.id
    """)
    suspend fun getVerbsByPattern(locale: String, pattern: String): List<VerbWithTranslation>

    @Query("""
    SELECT v.*, COALESCE(t.translation, '') as translation
    FROM verbs v
    LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
    ORDER BY RANDOM()
    LIMIT :limit
    """)
    suspend fun getRandomVerbs(locale: String, limit: Int): List<VerbWithTranslation>

    @Query("SELECT answerHistory FROM verbs WHERE id = :verbId")
    suspend fun getAnswersMaskById(verbId: Int): String?

    @Query("""
        SELECT v.id,
        v.infinitive,
        COALESCE(t.translation, '') as translation,
        v.answerHistory,
        v.isLearning
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        WHERE v.answerHistory IS NOT NULL
        ORDER BY v.id
    """)
    suspend fun getVerbsWithStatistic(locale: String): List<VerbsStatisticEntity>

    @Query("""
        SELECT 
            v.*,
            COALESCE(t.translation, '') as translation
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        WHERE v.isLearning = 1
        ORDER BY v.id
    """)
    suspend fun getLearningVerbs(locale: String): List<VerbWithTranslation>

    @Query("""
        SELECT v.*, COALESCE(t.translation, '') as translation
        FROM verbs v
        LEFT JOIN translations t ON v.id = t.verbId AND t.locale = :locale
        WHERE v.isLearning = 1 AND v.answerHistory is NULL
        ORDER BY v.id
    """)
    suspend fun getVerbsUnderLearnNotTested(locale: String): List<VerbWithTranslation>

    @Query("""
        SELECT v.*, COALESCE(t.translation, '') AS translation
        FROM verbs v
        LEFT JOIN translations t 
            ON v.id = t.verbId 
            AND t.locale = :locale
        WHERE v.isLearning = 1
          AND v.answerHistory IS NOT NULL
          AND (
              (LENGTH(v.answerHistory) - 
               LENGTH(REPLACE(v.answerHistory, '1', ''))) * 100
              / LENGTH(v.answerHistory)
          ) BETWEEN :minPercent AND :maxPercent
        ORDER BY v.id
    """)
    fun getLearningVerbsByAnswerPercent(locale: String, minPercent: Int, maxPercent: Int): List<VerbWithTranslation>

    @Query("UPDATE verbs SET isLearning = :isLearning WHERE id = :verbId")
    suspend fun updateLearningStatus(verbId: Int, isLearning: Boolean)

    @Transaction
    suspend fun updateLearningStatuses(changes: Map<Int, Boolean>) {
        changes.forEach { (verbId, isLearning) ->
            updateLearningStatus(verbId, isLearning)
        }
    }

    @Query("SELECT COUNT(*) FROM verbs")
    suspend fun getVerbsCount(): Int

    @Query("SELECT COUNT(*) FROM verbs WHERE frequency = :frequency")
    suspend fun getVerbsCountByFrequency(frequency: String): Int

    @Query("SELECT COUNT(*) FROM verbs WHERE pattern = :pattern")
    suspend fun getVerbsCountByPattern(pattern: String): Int

    @Query("""
        SELECT COUNT(*)
        FROM verbs
        WHERE isLearning = 1
    """)
    suspend fun getVerbsCountByVerbsUnderLearnAll(): Int

    @Query("""
        SELECT COUNT(*)
        FROM verbs
        WHERE isLearning = 1
          AND answerHistory IS NULL
    """)
    suspend fun getVerbsCountByVerbsUnderLearnNotTested(): Int

    @Query("""
        SELECT COUNT(*)
        FROM verbs
        WHERE isLearning = 1
          AND answerHistory IS NOT NULL
          AND LENGTH(answerHistory) > 0
          AND (
              1.0 * (
                  LENGTH(answerHistory) -
                  LENGTH(REPLACE(answerHistory, '1', ''))
              ) / LENGTH(answerHistory)
          ) BETWEEN :minPercent / 100.0 AND :maxPercent / 100.0
    """)
    suspend fun getVerbsCountByVerbsUnderLearnNeedPractice(
        minPercent: Int,
        maxPercent: Int
    ): Int

}