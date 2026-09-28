package com.azhel.gogonedemo.settings.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ThemeIcon: ImageVector
    get() {
        if (_theme != null) {
            return _theme!!
        }

        _theme = ImageVector.Builder(
            name = "Theme",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 3f)
                verticalLineTo(1f)
                horizontalLineTo(10f)
                verticalLineTo(3f)
                close()

                moveTo(12f, 21f)
                verticalLineTo(23f)
                horizontalLineTo(10f)
                verticalLineTo(21f)
                close()

                moveTo(4.22f, 5.64f)
                lineTo(2.81f, 4.22f)
                lineTo(1.39f, 5.64f)
                lineTo(2.8f, 7.05f)
                close()

                moveTo(20.78f, 18.36f)
                lineTo(22.19f, 19.77f)
                lineTo(20.78f, 21.19f)
                lineTo(19.36f, 19.77f)
                close()

                moveTo(3f, 11f)
                horizontalLineTo(1f)
                verticalLineTo(13f)
                horizontalLineTo(3f)
                close()

                moveTo(23f, 11f)
                horizontalLineTo(21f)
                verticalLineTo(13f)
                horizontalLineTo(23f)
                close()

                moveTo(4.22f, 18.36f)
                lineTo(2.8f, 19.77f)
                lineTo(4.22f, 21.19f)
                lineTo(5.63f, 19.77f)
                close()

                moveTo(20.78f, 5.64f)
                lineTo(22.2f, 4.22f)
                lineTo(23.61f, 5.64f)
                lineTo(22.2f, 7.05f)
                close()

                moveTo(12f, 18f)
                curveTo(8.69f, 18f, 6f, 15.31f, 6f, 12f)
                curveTo(6f, 8.69f, 8.69f, 6f, 12f, 6f)
                curveTo(15.31f, 6f, 18f, 8.69f, 18f, 12f)
                curveTo(18f, 15.31f, 15.31f, 18f, 12f, 18f)
                close()
            }
        }.build()

        return _theme!!
    }

private var _theme: ImageVector? = null