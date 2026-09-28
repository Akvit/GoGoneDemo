package com.azhel.gogonedemo.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded

class VerbWithTranslation(
    @Embedded val verb: VerbsTable,
    @ColumnInfo(name = "translation") val translation: String
)
