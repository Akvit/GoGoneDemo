package com.azhel.gogonedemo.designsystem.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {
    private var _info: ImageVector? = null
    val Info: ImageVector
        get() {
            if (_info != null) return _info!!
            _info = ImageVector.Builder(
                name = "Info",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                ) {
                    moveTo(12.0f, 21.0f)
                    arcTo(9.0f, 9.0f, 0.0f, isMoreThanHalf = true, isPositiveArc = false, 12.0f, 3.0f)
                    arcTo(9.0f, 9.0f, 0.0f, isMoreThanHalf = false, isPositiveArc = false, 12.0f, 21.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color.Black),
                    stroke = null
                ) {
                    moveTo(11.0f, 10.0f)
                    horizontalLineTo(13.0f)
                    verticalLineTo(17.0f)
                    horizontalLineTo(11.0f)
                    close()
                    moveTo(11.0f, 7.0f)
                    horizontalLineTo(13.0f)
                    verticalLineTo(9.0f)
                    horizontalLineTo(11.0f)
                    close()
                }
            }.build()
            return _info!!
        }

    private var _arrowBack: ImageVector? = null
    val ArrowBack: ImageVector
        get() {
            if (_arrowBack != null) return _arrowBack!!
            _arrowBack = ImageVector.Builder(
                name = "ArrowBack",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(19.0f, 12.0f)
                    horizontalLineTo(5.0f)
                    moveTo(11.0f, 6.0f)
                    lineTo(5.0f, 12.0f)
                    lineTo(11.0f, 18.0f)
                }
            }.build()
            return _arrowBack!!
        }

    private var _arrowUp: ImageVector? = null
    val ArrowUp: ImageVector
        get() {
            if (_arrowUp != null) return _arrowUp!!
            _arrowUp = ImageVector.Builder(
                name = "ArrowUp",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(6.0f, 14.0f)
                    lineTo(12.0f, 8.0f)
                    lineTo(18.0f, 14.0f)
                }
            }.build()
            return _arrowUp!!
        }

    private var _arrowDown: ImageVector? = null
    val ArrowDown: ImageVector
        get() {
            if (_arrowDown != null) return _arrowDown!!
            _arrowDown = ImageVector.Builder(
                name = "ArrowDown",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(6.0f, 10.0f)
                    lineTo(12.0f, 16.0f)
                    lineTo(18.0f, 10.0f)
                }
            }.build()
            return _arrowDown!!
        }

    private var _list: ImageVector? = null
    val List: ImageVector
        get() {
            if (_list != null) return _list!!
            _list = ImageVector.Builder(
                name = "List",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M9,6h10M9,12h10M9,18h10").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
                addPath(
                    PathParser().parsePathString("M4,5h2v2H4zM4,11h2v2H4zM4,17h2v2H4z").toNodes(),
                    fill = SolidColor(Color.Black)
                )
            }.build()
            return _list!!
        }

    private var _check: ImageVector? = null
    val Check: ImageVector
        get() {
            if (_check != null) return _check!!
            _check = ImageVector.Builder(
                name = "Check",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M5,12l4,4L19,7").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _check!!
        }

    private var _checked: ImageVector? = null
    val Checked: ImageVector
        get() {
            if (_checked != null) return _checked!!
            _checked = ImageVector.Builder(
                name = "Checked",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M12,21a9,9 0,1 0,0 -18a9,9 0,0 0,0 18z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
                addPath(
                    PathParser().parsePathString("M9,12l2,2 4,-4").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _checked!!
        }

    private var _warning: ImageVector? = null
    val Warning: ImageVector
        get() {
            if (_warning != null) return _warning!!
            _warning = ImageVector.Builder(
                name = "Warning",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M12,4L21,20H3L12,4Z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
                addPath(
                    PathParser().parsePathString("M12,9v5").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
                addPath(
                    PathParser().parsePathString("M11,16h2v2h-2z").toNodes(),
                    fill = SolidColor(Color.Black)
                )
            }.build()
            return _warning!!
        }

    private var _add: ImageVector? = null
    val Add: ImageVector
        get() {
            if (_add != null) return _add!!
            _add = ImageVector.Builder(
                name = "Add",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M12,21a9,9 0,1 0,0 -18a9,9 0,0 0,0 18z").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
                addPath(
                    PathParser().parsePathString("M12,8v8M8,12h8").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round
                )
            }.build()
            return _add!!
        }

    private var _remove: ImageVector? = null
    val Remove: ImageVector
        get() {
            if (_remove != null) return _remove!!
            _remove = ImageVector.Builder(
                name = "Remove",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                addPath(
                    PathParser().parsePathString("M4,7h16M9,7V4h6v3M6,7v12a2,2 0,0 0,2 2h8a2,2 0,0 0,2 -2V7M10,11v6M14,11v6").toNodes(),
                    fill = null,
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                )
            }.build()
            return _remove!!
        }
}
