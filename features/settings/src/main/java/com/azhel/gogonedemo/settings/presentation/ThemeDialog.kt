package com.azhel.gogonedemo.settings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.setings.api.AppTheme
import com.azhel.gogonedemo.settings.R

@Composable
fun ThemeDialog(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.theme))
        },
        text = {
            Column {
                AppTheme.entries.forEach { theme ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onThemeSelected(theme)
                            }
                            .padding(vertical = dp12),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = theme == currentTheme,
                            onClick = {
                                onThemeSelected(theme)
                            }
                        )

                        Spacer(modifier = Modifier.width(dp12))

                        Text(
                            text = theme.toDisplayName()
                        )
                    }
                }
            }
        },
        confirmButton = {}
    )
}