package com.azhel.gogonedemo.domain

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerializable

@Serializable
sealed interface ProcessSetup : JavaSerializable {

    @Serializable
    data class Pattern(val verbsPatternEnum: VerbsPatternEnum): ProcessSetup

    @Serializable
    data class Frequency(val verbsFrequencyEnum: VerbsFrequencyEnum): ProcessSetup

    @Serializable
    data class Number(val numberOfVerbs: Int): ProcessSetup

    @Serializable
    object All: ProcessSetup

    @Serializable
    data class VerbsUnderLearn(val progressPrecent: LearningVerbsFilter): ProcessSetup

}
