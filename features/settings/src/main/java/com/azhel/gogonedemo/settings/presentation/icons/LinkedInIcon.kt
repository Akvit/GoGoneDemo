package com.azhel.gogonedemo.settings.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LinkedInIcon: ImageVector
    get() {
        if (_linkedIn != null) {
            return _linkedIn!!
        }

        _linkedIn = ImageVector.Builder(
            name = "LinkedIn",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20.45f, 20.45f)
                horizontalLineTo(16.89f)
                verticalLineTo(14.87f)
                curveTo(
                    16.89f, 13.54f,
                    16.86f, 11.83f,
                    15.03f, 11.83f
                )
                curveTo(
                    13.17f, 11.83f,
                    12.89f, 13.28f,
                    12.89f, 14.78f
                )
                verticalLineTo(20.45f)
                horizontalLineTo(9.33f)
                verticalLineTo(8.99f)
                horizontalLineTo(12.74f)
                verticalLineTo(10.56f)
                horizontalLineTo(12.79f)
                curveTo(
                    13.27f, 9.66f,
                    14.42f, 8.71f,
                    16.16f, 8.71f
                )
                curveTo(
                    19.77f, 8.71f,
                    20.45f, 11.09f,
                    20.45f, 14.18f
                )
                verticalLineTo(20.45f)
                close()

                moveTo(5.31f, 7.42f)
                curveTo(
                    4.16f, 7.42f,
                    3.23f, 6.49f,
                    3.23f, 5.34f
                )
                curveTo(
                    3.23f, 4.19f,
                    4.16f, 3.26f,
                    5.31f, 3.26f
                )
                curveTo(
                    6.46f, 3.26f,
                    7.39f, 4.19f,
                    7.39f, 5.34f
                )
                curveTo(
                    7.39f, 6.49f,
                    6.46f, 7.42f,
                    5.31f, 7.42f
                )
                close()

                moveTo(3.59f, 8.99f)
                horizontalLineTo(7.04f)
                verticalLineTo(20.45f)
                horizontalLineTo(3.59f)
                close()
            }
        }.build()

        return _linkedIn!!
    }

private var _linkedIn: ImageVector? = null