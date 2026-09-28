package com.azhel.gogonedemo.settings.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Tips: ImageVector
    get() {
        if (_tips != null) return _tips!!

        _tips = ImageVector.Builder(
            name = "Tips",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {

            path(
                fill = androidx.compose.ui.graphics.SolidColor(Color.Black),
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9f, 21f)
                horizontalLineTo(15f)
                verticalLineTo(19f)
                horizontalLineTo(9f)
                close()

                moveTo(12f, 2f)
                curveTo(7.58f, 2f, 4f, 5.58f, 4f, 10f)
                curveTo(4f, 13.1f, 5.7f, 15.82f, 8.2f, 17.2f)
                verticalLineTo(18f)
                horizontalLineTo(15.8f)
                verticalLineTo(17.2f)
                curveTo(18.3f, 15.82f, 20f, 13.1f, 20f, 10f)
                curveTo(20f, 5.58f, 16.42f, 2f, 12f, 2f)
                close()

                moveTo(12f, 4f)
                curveTo(15.31f, 4f, 18f, 6.69f, 18f, 10f)
                curveTo(18f, 12.25f, 16.78f, 14.23f, 15f, 15.3f)
                verticalLineTo(16f)
                horizontalLineTo(9f)
                verticalLineTo(15.3f)
                curveTo(7.22f, 14.23f, 6f, 12.25f, 6f, 10f)
                curveTo(6f, 6.69f, 8.69f, 4f, 12f, 4f)
                close()
            }

            // Искра слева сверху
            path(
                fill = androidx.compose.ui.graphics.SolidColor(Color.Black)
            ) {
                moveTo(3f, 3f)
                horizontalLineTo(4f)
                verticalLineTo(5f)
                horizontalLineTo(3f)
                close()

                moveTo(2f, 4f)
                horizontalLineTo(5f)
                verticalLineTo(3f)
                horizontalLineTo(2f)
                close()
            }

            path(
                fill = androidx.compose.ui.graphics.SolidColor(Color.Black)
            ) {
                moveTo(20f, 3f)
                horizontalLineTo(21f)
                verticalLineTo(6f)
                horizontalLineTo(20f)
                close()

                moveTo(19f, 4f)
                horizontalLineTo(22f)
                verticalLineTo(5f)
                horizontalLineTo(19f)
                close()
            }

        }.build()

        return _tips!!
    }

private var _tips: ImageVector? = null