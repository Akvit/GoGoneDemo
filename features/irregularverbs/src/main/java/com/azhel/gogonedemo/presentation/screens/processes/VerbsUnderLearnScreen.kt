package com.azhel.gogonedemo.presentation.screens.processes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.result.ResultEffect
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.dp24
import com.azhel.gogonedemo.designsystem.dp4
import com.azhel.gogonedemo.designsystem.dp72
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.designsystem.uielements.AppListItemCard
import com.azhel.gogonedemo.designsystem.uielements.ErrorScreen
import com.azhel.gogonedemo.designsystem.uielements.LoadingContent
import com.azhel.gogonedemo.designsystem.uielements.NavTopAppBar
import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.DeleteVerbFromListEvent
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.icons.FeatureIcons
import com.azhel.gogonedemo.presentation.viewmodels.VerbsUnderLearnViewModel
import com.azhel.gogonedemo.presentation.contracts.VerbsUnderLearnIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsUnderLearnState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun VerbsUnderLearnScreen() {
    val viewModel = koinViewModel<VerbsUnderLearnViewModel>()
    val navigator: Navigator = koinInject()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val languageCode = Locale.current.language

    LaunchedEffect(languageCode) {
        viewModel.onIntent(
            VerbsUnderLearnIntent.Load(local = languageCode, processSetup = ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.ALL))
        )
    }

    ResultEffect<DeleteVerbFromListEvent> { event ->
        viewModel.onIntent(VerbsUnderLearnIntent.Delete(event.verbModel))
    }

    val deleteVerbText = stringResource(R.string.delete_verb_from_list_learn_sure)

    when {
        state.isLoading -> LoadingContent()
        state.errorText != null -> ErrorScreen(
            errorText = state.errorText.orEmpty(),
            onRetry = {
                viewModel.onIntent(
                    VerbsUnderLearnIntent.Load(local = languageCode, processSetup = ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.ALL))
                )
            },
            onBack = navigator::removeLast
        )
        else -> LearningVerbsLayout(
            state = state,
            onAddVerbsClick = navigator::removeLast,
            onDeleteClick = { verbToDelete ->
                navigator.add(
                    InnerRoutes.DeleteVerbFromListSureDialog(
                        deleteVerbText.format(verbToDelete.infinitive),
                        verbToDelete
                    )
                )
            },
            onBackClick = navigator::removeLast
        )
    }
}

@Composable
private fun LearningVerbsLayout(
    state: VerbsUnderLearnState,
    onAddVerbsClick: () -> Unit,
    onDeleteClick: (VerbModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            NavTopAppBar(
                title = stringResource(R.string.learning_verbs_title),
                onBackClick = onBackClick
            )
        }
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .padding(dp12)
        ) {

            if (state.list.isEmpty()) {
                EmptyLearningVerbs(onAddVerbsClick = onAddVerbsClick)
            } else {
                LearningVerbsList(verbs = state.list, onDeleteClick = onDeleteClick)
            }
        }
    }
}

@Composable
private fun EmptyLearningVerbs(onAddVerbsClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dp16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = FeatureIcons.EmptyLearning,
            contentDescription = null,
            modifier = Modifier.size(dp72),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(dp24))

        Text(
            text = stringResource(R.string.learning_verbs_empty_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(dp12))

        Text(
            text = stringResource(R.string.learning_verbs_empty_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(dp24))

        Button(
            onClick = onAddVerbsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = AppIcons.Add,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(dp8))
            Text(text = stringResource(R.string.add_verbs))
        }

        Spacer(modifier = Modifier.height(dp24))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Row(
                modifier = Modifier.padding(dp16),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = AppIcons.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(modifier = Modifier.width(dp12))

                Column {
                    Text(
                        text = stringResource(R.string.learning_verbs_recommendation_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(dp4))
                    Text(
                        text = stringResource(R.string.learning_verbs_recommendation_description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun LearningVerbsList(verbs: List<VerbModel>, onDeleteClick: (VerbModel) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(dp8),
        contentPadding = PaddingValues(vertical = dp16)
    ) {
        item {
            Text(
                text = stringResource(R.string.learning_verbs_count, verbs.size),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = dp8)
            )
        }
        items(verbs) { verb ->
            LearningVerbItem(verb = verb, onDeleteClick = onDeleteClick)
        }
    }
}

@Composable
private fun LearningVerbItem(verb: VerbModel, onDeleteClick: (VerbModel) -> Unit) {
    AppListItemCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = verb.infinitive,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(dp4))
                Text(
                    text = verb.translation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(dp4))
                Text(
                    text = "${verb.infinitive} — ${verb.pastSimple} — ${verb.pastParticipate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                modifier = Modifier.clickable { onDeleteClick(verb) },
                imageVector = AppIcons.Remove,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
