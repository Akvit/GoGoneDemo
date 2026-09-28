package com.azhel.gogonedemo.presentation.screens.resultscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.dp24
import com.azhel.gogonedemo.designsystem.dp48
import com.azhel.gogonedemo.designsystem.dp64
import com.azhel.gogonedemo.designsystem.dp72
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.InnerRoutes
import org.koin.compose.koinInject

@Composable
fun LearningResultScreen(processSetup: ProcessSetup) {
    val navigator: Navigator = koinInject()
    LearningResultLayout(navigator::removeLast)
}

@Composable
private fun LearningResultLayout(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dp24),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(dp64))

        Text(
            text = stringResource(R.string.learning_completed_title),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(dp48))

        Icon(
            imageVector = AppIcons.Checked,
            contentDescription = null,
            modifier = Modifier.size(dp72),
            tint = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(dp24))

        Text(
            text = stringResource(R.string.learning_completed_description),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = onBack,
            modifier = modifier,
        ) {
            Text(stringResource(R.string.learning_completed_home))
        }
        Spacer(modifier = Modifier.height(dp24))
    }
}