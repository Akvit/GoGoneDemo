package com.azhel.gogonedemo.designsystem.uielements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.azhel.gogonedemo.designsystem.dp1
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons

@Composable
fun InfoCard(
    text: String,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        border = BorderStroke(
            width = dp1,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Column(modifier = Modifier.padding(dp12), horizontalAlignment = Alignment.End) {

            Icon(
                modifier = Modifier.size(dp16).clickable(onClick = onCloseClick),
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = AppIcons.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(
                    modifier = Modifier.width(dp12)
                )

                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    InfoCard("description description description description description description description description description description description description description description description description description description description description description description description ", {})
}

@Preview
@Composable
private fun InfoCardPreviewSmall() {
    InfoCard("description description description ", {})
}