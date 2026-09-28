package com.azhel.gogonedemo.designsystem.uielements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.azhel.gogonedemo.designsystem.dp24
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.R

@Composable
fun ErrorScreen(
    errorText: String,
    onRetry: (() -> Unit)? = null,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dp24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorText.ifBlank { stringResource(R.string.error_occurred) },
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(dp24))

        onRetry?.let {
            Button(
                onClick = it,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.retry))
            }
        }

        Spacer(modifier = Modifier.height(dp8))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}
