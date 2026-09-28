package com.azhel.gogonedemo.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.PathParser

object FeatureIcons {

    private var _test: ImageVector? = null
    val Test: ImageVector
        get() {
            if (_test != null) return _test!!
            _test = ImageVector.Builder(
                name = "Test",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M6,4h12a2,2 0,0 1,2 2v12a2,2 0,0 1,-2 2H6a2,2 0,0 1,-2 -2V6a2,2 0,0 1,2 -2z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M8,12l3,3 5,-6").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _test!!
        }

    private var _learn: ImageVector? = null
    val Learn: ImageVector
        get() {
            if (_learn != null) return _learn!!
            _learn = ImageVector.Builder(
                name = "Learn",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M4,5h6a2,2 0,0 1,2 2v12a2,2 0,0 0,-2 -2H4z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M20,5h-6a2,2 0,0 0,-2 2v12a2,2 0,0 1,2 -2h6z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _learn!!
        }

    private var _learned: ImageVector? = null
    val Learned: ImageVector
        get() {
            if (_learned != null) return _learned!!
            _learned = ImageVector.Builder(
                name = "Learned",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,12l4,4 10,-10").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _learned!!
        }

    private var _pattern: ImageVector? = null
    val Pattern: ImageVector
        get() {
            if (_pattern != null) return _pattern!!
            _pattern = ImageVector.Builder(
                name = "Pattern",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,7h4M15,7h4M5,17h4M15,17h4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
                addPath(
                    PathParser().parsePathString("M9,7h6M9,17h6").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _pattern!!
        }

    private var _shuffle: ImageVector? = null
    val Shuffle: ImageVector
        get() {
            if (_shuffle != null) return _shuffle!!
            _shuffle = ImageVector.Builder(
                name = "Shuffle",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M3,7h2c4,0 6,10 10,10h3").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M16,14l3,3 -3,3").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M3,17h2c1.5,0 2.7,-1.2 3.7,-2.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M15.5,7H19").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M16,4l3,3 -3,3").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _shuffle!!
        }

    private var _marathon: ImageVector? = null
    val Marathon: ImageVector
        get() {
            if (_marathon != null) return _marathon!!
            _marathon = ImageVector.Builder(
                name = "Marathon",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,20V4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M5,5C9,3 11,7 15,5C17,4 18,4 19,5V13C18,12 17,12 15,13C11,15 9,11 5,13").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _marathon!!
        }

    private var _practice: ImageVector? = null
    val Practice: ImageVector
        get() {
            if (_practice != null) return _practice!!
            _practice = ImageVector.Builder(
                name = "Practice",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M19,8a7,7 0,0 0,-12 -1L5,9").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M5,5v4h4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M5,16a7,7 0,0 0,12 1l2,-2").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M19,19v-4h-4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _practice!!
        }

    private var _allVerbs: ImageVector? = null
    val AllVerbs: ImageVector
        get() {
            if (_allVerbs != null) return _allVerbs!!
            _allVerbs = ImageVector.Builder(
                name = "AllVerbs",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,6h14M5,12h14M5,18h14").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _allVerbs!!
        }

    private var _frequency: ImageVector? = null
    val Frequency: ImageVector
        get() {
            if (_frequency != null) return _frequency!!
            _frequency = ImageVector.Builder(
                name = "Frequency",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,18V12M12,18V7M19,18V12").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _frequency!!
        }

    private var _unlearned: ImageVector? = null
    val Unlearned: ImageVector
        get() {
            if (_unlearned != null) return _unlearned!!
            _unlearned = ImageVector.Builder(
                name = "Unlearned",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M12,5v14M5,12h14").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _unlearned!!
        }

    private var _collection: ImageVector? = null
    val Collection: ImageVector
        get() {
            if (_collection != null) return _collection!!
            _collection = ImageVector.Builder(
                name = "Collection",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M6,4h11a2,2 0,0 1,2 2v12a2,2 0,0 1,-2 2H6a2,2 0,0 1,-2 -2V6a2,2 0,0 1,2 -2z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M8,8h7M8,12h7M8,16h4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _collection!!
        }

    private var _statistics: ImageVector? = null
    val Statistics: ImageVector
        get() {
            if (_statistics != null) return _statistics!!
            _statistics = ImageVector.Builder(
                name = "Statistics",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,4v16h15").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M8,16l3,-3 3,2 4,-5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _statistics!!
        }

    private var _patternAaa: ImageVector? = null
    val PatternAaa: ImageVector
        get() {
            if (_patternAaa != null) return _patternAaa!!
            _patternAaa = ImageVector.Builder(
                name = "PatternAaa",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M3,10L6,2L9,10M4.5,7H7.5 M9,21L12,13L15,21M10.5,18H13.5 M15,10L18,2L21,10M16.5,7H19.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _patternAaa!!
        }

    private var _patternAba: ImageVector? = null
    val PatternAba: ImageVector
        get() {
            if (_patternAba != null) return _patternAba!!
            _patternAba = ImageVector.Builder(
                name = "PatternAba",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M3,10L6,2L9,10M4.5,7H7.5 M15,10L18,2L21,10M16.5,7H19.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M9.5,13V21H12.5A2,2 0,0 0,12.5,17H9.5 M12.5,17A2,2 0,0 0,12.5,13H9.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _patternAba!!
        }

    private var _patternAbb: ImageVector? = null
    val PatternAbb: ImageVector
        get() {
            if (_patternAbb != null) return _patternAbb!!
            _patternAbb = ImageVector.Builder(
                name = "PatternAbb",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M3,10L6,2L9,10M4.5,7H7.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M9.5,13V21H12.5A2,2 0,0 0,12.5,17H9.5 M12.5,17A2,2 0,0 0,12.5,13H9.5 M15.5,2V10H18.5A2,2 0,0 0,18.5,6H15.5 M18.5,6A2,2 0,0 0,18.5,2H15.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _patternAbb!!
        }

    private var _patternAbc: ImageVector? = null
    val PatternAbc: ImageVector
        get() {
            if (_patternAbc != null) return _patternAbc!!
            _patternAbc = ImageVector.Builder(
                name = "PatternAbc",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M3,10L6,2L9,10M4.5,7H7.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M9.5,13V21H12.5A2,2 0,0 0,12.5,17H9.5 M12.5,17A2,2 0,0 0,12.5,13H9.5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M21,4A3,3 0,1 0,21,8").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 1.2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _patternAbc!!
        }

    private var _frequencyLow: ImageVector? = null
    val FrequencyLow: ImageVector
        get() {
            if (_frequencyLow != null) return _frequencyLow!!
            _frequencyLow = ImageVector.Builder(
                name = "FrequencyLow",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,18V17M12,18V15M19,18V12").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _frequencyLow!!
        }

    private var _emptyLearning: ImageVector? = null
    val EmptyLearning: ImageVector
        get() {
            if (_emptyLearning != null) return _emptyLearning!!
            _emptyLearning = ImageVector.Builder(
                name = "EmptyLearning",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M12,19C10,17.5 8,17 5,17V5c3,0 5,0.5 7,2M12,19c2,-1.5 4,-2 7,-2V5c-3,0 -5,0.5 -7,2").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _emptyLearning!!
        }

    private var _frequencyHigh: ImageVector? = null
    val FrequencyHigh: ImageVector
        get() {
            if (_frequencyHigh != null) return _frequencyHigh!!
            _frequencyHigh = ImageVector.Builder(
                name = "FrequencyHigh",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,18V14M12,18V9M19,18V5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _frequencyHigh!!
        }

    private var _frequencyMedium: ImageVector? = null
    val FrequencyMedium: ImageVector
        get() {
            if (_frequencyMedium != null) return _frequencyMedium!!
            _frequencyMedium = ImageVector.Builder(
                name = "FrequencyMedium",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,18V16M12,18V12M19,18V8").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _frequencyMedium!!
        }

    private var _verbsUnderLearn: ImageVector? = null
    val VerbsUnderLearn: ImageVector
        get() {
            if (_verbsUnderLearn != null) return _verbsUnderLearn!!
            _verbsUnderLearn = ImageVector.Builder(
                name = "VerbsUnderLearn",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,5h10a2,2 0,0 1,2 2v12H7a2,2 0,0 1,-2 -2z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M17,19l2,-2V5a2,2 0,0 0,-2 -2H7").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M8,9h5M8,13h4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _verbsUnderLearn!!
        }
}

private var _searchOff: ImageVector? = null

val SearchOff: ImageVector
    get() {
        if (_searchOff != null) return _searchOff!!

        _searchOff = ImageVector.Builder(
            name = "SearchOff",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            addPath(
                PathParser()
                    .parsePathString(
                        "M15.5,14h-0.79l-0.28,-0.27C15.63,12.06 16.5,9.77 16.5,7.5C16.5,2.81 12.69,-1 8,-1C3.31,-1 -0.5,2.81 -0.5,7.5C-0.5,12.19 3.31,16 8,16C10.27,16 12.56,15.13 14.23,13.93L14.5,14.21v0.79l5,4.99L20.49,19l-4.99,-5zM8,14C4.41,14 1.5,11.09 1.5,7.5C1.5,3.91 4.41,1 8,1C11.59,1 14.5,3.91 14.5,7.5C14.5,11.09 11.59,14 8,14z"
                    )
                    .toNodes(),
                fill = SolidColor(Color.Black)
            )

            addPath(
                PathParser()
                    .parsePathString(
                        "M4.27,3.27L3,4.54L6.46,8L3,11.46L4.27,12.73L7.73,9.27L11.19,12.73L12.46,11.46L9,8L12.46,4.54L11.19,3.27L7.73,6.73L4.27,3.27Z"
                    )
                    .toNodes(),
                fill = SolidColor(Color.Black)
            )
        }.build()

        return _searchOff!!
    }