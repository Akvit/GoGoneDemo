package com.azhel.gogonedemo.designsystem.uielements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.azhel.gogonedemo.designsystem.dp4
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons.ArrowBack

@Composable
fun NavTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = dp4
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor,
        tonalElevation = elevation,
        shadowElevation = elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = ArrowBack,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(dp8))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = contentColor,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
