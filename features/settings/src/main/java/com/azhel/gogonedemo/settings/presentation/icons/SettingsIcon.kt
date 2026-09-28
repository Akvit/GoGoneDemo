package com.azhel.gogonedemo.settings.presentation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SettingsIcon: ImageVector
    get() {
        if (_settings != null) {
            return _settings!!
        }

        _settings = ImageVector.Builder(
            name = "Settings",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(19.43f, 12.98f)
                curveTo(19.47f, 12.66f, 19.5f, 12.33f, 19.5f, 12f)
                curveTo(19.5f, 11.67f, 19.47f, 11.34f, 19.43f, 11.02f)

                lineTo(21.54f, 9.37f)
                curveTo(21.73f, 9.22f, 21.78f, 8.95f, 21.66f, 8.73f)

                lineTo(19.66f, 5.27f)
                curveTo(19.54f, 5.05f, 19.29f, 4.96f, 19.06f, 5.05f)

                lineTo(16.57f, 6.05f)
                curveTo(16.05f, 5.65f, 15.49f, 5.32f, 14.88f, 5.07f)

                lineTo(14.5f, 2.42f)
                curveTo(14.46f, 2.18f, 14.25f, 2f, 14f, 2f)

                horizontalLineTo(10f)
                curveTo(9.75f, 2f, 9.54f, 2.18f, 9.5f, 2.42f)

                lineTo(9.12f, 5.07f)
                curveTo(8.51f, 5.32f, 7.95f, 5.65f, 7.43f, 6.05f)

                lineTo(4.94f, 5.05f)
                curveTo(4.71f, 4.96f, 4.46f, 5.05f, 4.34f, 5.27f)

                lineTo(2.34f, 8.73f)
                curveTo(2.22f, 8.95f, 2.27f, 9.22f, 2.46f, 9.37f)

                lineTo(4.57f, 11.02f)
                curveTo(4.53f, 11.34f, 4.5f, 11.67f, 4.5f, 12f)
                curveTo(4.5f, 12.33f, 4.53f, 12.66f, 4.57f, 12.98f)

                lineTo(2.46f, 14.63f)
                curveTo(2.27f, 14.78f, 2.22f, 15.05f, 2.34f, 15.27f)

                lineTo(4.34f, 18.73f)
                curveTo(4.46f, 18.95f, 4.71f, 19.04f, 4.94f, 18.95f)

                lineTo(7.43f, 17.95f)
                curveTo(7.95f, 18.35f, 8.51f, 18.68f, 9.12f, 18.93f)

                lineTo(9.5f, 21.58f)
                curveTo(9.54f, 21.82f, 9.75f, 22f, 10f, 22f)

                horizontalLineTo(14f)
                curveTo(14.25f, 22f, 14.46f, 21.82f, 14.5f, 21.58f)

                lineTo(14.88f, 18.93f)
                curveTo(15.49f, 18.68f, 16.05f, 18.35f, 16.57f, 17.95f)

                lineTo(19.06f, 18.95f)
                curveTo(19.29f, 19.04f, 19.54f, 18.95f, 19.66f, 18.73f)

                lineTo(21.66f, 15.27f)
                curveTo(21.78f, 15.05f, 21.73f, 14.78f, 21.54f, 14.63f)

                lineTo(19.43f, 12.98f)
                close()

                moveTo(12f, 15.5f)
                curveTo(10.07f, 15.5f, 8.5f, 13.93f, 8.5f, 12f)
                curveTo(8.5f, 10.07f, 10.07f, 8.5f, 12f, 8.5f)
                curveTo(13.93f, 8.5f, 15.5f, 10.07f, 15.5f, 12f)
                curveTo(15.5f, 13.93f, 13.93f, 15.5f, 12f, 15.5f)
                close()
            }
        }.build()

        return _settings!!
    }

private var _settings: ImageVector? = null