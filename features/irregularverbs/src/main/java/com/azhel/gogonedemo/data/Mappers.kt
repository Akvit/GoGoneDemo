package com.azhel.gogonedemo.data

import com.azhel.gogonedemo.data.entities.VerbWithTranslation
import com.azhel.gogonedemo.data.entities.VerbsStatisticEntity
import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.domain.models.VerbStatisticModel

fun VerbWithTranslation.toModel() = VerbModel(
    id = this.verb.id,
    infinitive = this.verb.infinitive,
    pastSimple = this.verb.pastSimple,
    pastParticipate = this.verb.pastParticipate,
    frequency = this.verb.frequency,
    pattern = this.verb.pattern,
    translation = this.translation,
    answersGiven = AnswersHandler.getAnswersCount(this.verb.answerHistory),
    rightAnswers = AnswersHandler.getRightAnswers(this.verb.answerHistory),
    isLearning = this.verb.isLearning
)

fun VerbsStatisticEntity.toModel() = VerbStatisticModel(
    verbId = id,
    infinitive = infinitive,
    translation = translation,
    answersGiven = AnswersHandler.getAnswersCount(answerHistory) ?: 0,
    rightAnswers = AnswersHandler.getRightAnswers(answerHistory) ?: 0,
    isUnderLearn = isLearning
)