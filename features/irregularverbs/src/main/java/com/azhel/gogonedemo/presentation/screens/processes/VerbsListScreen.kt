package com.azhel.gogonedemo.presentation.screens.processes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.GoGoneDemoTheme
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.dp24
import com.azhel.gogonedemo.designsystem.dp28
import com.azhel.gogonedemo.designsystem.dp32
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.designsystem.uielements.AppListItemCard
import com.azhel.gogonedemo.designsystem.uielements.ErrorScreen
import com.azhel.gogonedemo.designsystem.uielements.InfoCard
import com.azhel.gogonedemo.designsystem.uielements.LoadingContent
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.designsystem.uielements.SearchTopAppBar
import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.icons.SearchOff
import com.azhel.gogonedemo.presentation.viewmodels.VerbsListViewModel
import com.azhel.gogonedemo.presentation.contracts.LearningVisualState
import com.azhel.gogonedemo.presentation.contracts.VerbModelUiState
import com.azhel.gogonedemo.presentation.contracts.VerbsListEffect
import com.azhel.gogonedemo.presentation.contracts.VerbsListIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsListState
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
internal fun VerbsListScreen(learnProcess: InnerRoutes.VerbsList) {

    val viewModel: VerbsListViewModel = koinViewModel<VerbsListViewModel>()
    val navigator: Navigator = koinInject()
    val languageCode = Locale.current.language

    LaunchedEffect(languageCode) {
        viewModel.onIntent(VerbsListIntent.Load(local = languageCode, processSetup = learnProcess.processSetup))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                VerbsListEffect.NavigateBack -> navigator.removeLast()
            }
        }
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value

    when {
        state.isLoading -> LoadingContent()
        state.error != null -> ErrorScreen(
            errorText = state.error,
            onRetry = { viewModel.onIntent(VerbsListIntent.Load(local = languageCode, processSetup = learnProcess.processSetup)) },
            onBack = navigator::removeLast
        )
        else -> VerbsListLayout(
            screenState = state,
            onBackClick = navigator::removeLast,
            onVerbClick = { verb ->
                viewModel.onIntent(VerbsListIntent.ToggleLearning(verb))
            },
            onSaveClick = {
                viewModel.onIntent(VerbsListIntent.SaveChanges)
            },
            onHideVerbsListScreenHint = {
                viewModel.onIntent(VerbsListIntent.HideVerbsListScreenHint)
            },
            onQueryChange = { viewModel.onIntent(VerbsListIntent.OnSearchTyping(it)) }
        )
    }

}

@Composable
private fun VerbsListLayout(
    screenState: VerbsListState,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onVerbClick: (VerbModelUiState) -> Unit,
    onHideVerbsListScreenHint: () -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchTopAppBar(
                title = stringResource(R.string.verbs_list),
                query = screenState.query,
                onQueryChange = onQueryChange,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            if (screenState.pendingLearningChanges.isNotEmpty()) {
                SaveLearningChangesButton(
                    onClick = onSaveClick
                )
            }
        }
    ) { innerPadding ->

        if (screenState.listOfFound.isEmpty()) {
            EmptyVerbsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(dp12),
                verticalArrangement = Arrangement.spacedBy(dp8)
            ) {

                if (screenState.isShowVerbsListScreenHint) {
                    item {
                        InfoCard(
                            text = stringResource(R.string.learning_verbs_hint),
                            onCloseClick = onHideVerbsListScreenHint
                        )

                        Spacer(
                            modifier = Modifier.height(dp8)
                        )
                    }

                }

                items(
                    items = screenState.listOfFound,
                    key = { it.verbModel.id }
                ) { verb ->

                    VerbListItem(
                        verb = verb,
                        onClick = {
                            onVerbClick(verb)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SaveLearningChangesButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dp16)
    ) {
        Text(
            text = stringResource(R.string.save_learning_changes)
        )
    }
}

@Composable private fun EmptyVerbsList( modifier: Modifier = Modifier ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dp24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center ) {
            Icon(
                imageVector = SearchOff,
                contentDescription = null,
                modifier = Modifier.size(dp32),
                tint = MaterialTheme.colorScheme.onSurfaceVariant )
            Spacer(modifier = Modifier.height(dp16))
            Text(
                text = stringResource(R.string.no_verbs_found_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dp8))
            Text(
                text = stringResource(R.string.no_verbs_found_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
    }
}

@Composable
private fun VerbListItem(
    verb: VerbModelUiState,
    onClick: () -> Unit
) {
    AppListItemCard(Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VerbForm(
                        value = verb.verbModel.infinitive,
                        modifier = Modifier.weight(1f)
                    )

                    VerbForm(
                        value = verb.verbModel.pastSimple,
                        modifier = Modifier.weight(1f)
                    )

                    VerbForm(
                        value = verb.verbModel.pastParticipate,
                        modifier = Modifier.weight(1f)
                    )
                }

                Text(
                    text = verb.verbModel.translation,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.width(dp12)
            )

            LearningStatusIcon(
                visualState = verb.learningVisualState
            )
        }
    }
}

@Composable
private fun VerbForm(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = value,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1
    )
}

@Composable
private fun LearningStatusIcon(
    visualState: LearningVisualState
) {
    val icon = when (visualState) {
        LearningVisualState.AlreadySelected,
        LearningVisualState.JustAdded -> {
            AppIcons.Checked
        }

        LearningVisualState.NotSelected -> {
            AppIcons.Add
        }

        LearningVisualState.JustRemoved -> {
            AppIcons.Remove
        }
    }

    val tint = when (visualState) {
        LearningVisualState.AlreadySelected -> {
            MaterialTheme.colorScheme.primary
        }

        LearningVisualState.NotSelected -> {
            MaterialTheme.colorScheme.onSurfaceVariant
        }

        LearningVisualState.JustAdded -> {
            MaterialTheme.colorScheme.primary
        }

        LearningVisualState.JustRemoved -> {
            MaterialTheme.colorScheme.error
        }
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier.size(dp28),
        tint = tint
    )
}

@Composable
@Preview
fun VerbsListLayoutPreviewEmpty() {
    GoGoneDemoTheme {
        Scaffold { innerPadding ->
            VerbsListLayout(
                screenState = VerbsListState(),
                onBackClick = { },
                onSaveClick = { },
                onVerbClick = { },
                onHideVerbsListScreenHint = { },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(dp16),
                onQueryChange = {  },
            )
        }
    }

}

@Composable
@Preview
fun VerbsListLayoutPreview() {
    GoGoneDemoTheme {
        Scaffold { innerPadding ->
            VerbsListLayout(
                screenState = VerbsListState(
                    listOf(
                        VerbModelUiState(
                            VerbModel(
                                id = 1,
                                infinitive = "go",
                                pastSimple = "went",
                                pastParticipate = "gone",
                                frequency = "high",
                                pattern = "ABC",
                                translation = "идти",
                                answersGiven = 10,
                                rightAnswers = 10,
                                isLearning = true
                            ), LearningVisualState.AlreadySelected
                        ),
                        VerbModelUiState(
                            VerbModel(
                                id = 2,
                                infinitive = "write",
                                pastSimple = "wrote",
                                pastParticipate = "written",
                                frequency = "medium",
                                pattern = "ABC",
                                translation = "писать",
                                answersGiven = 10,
                                rightAnswers = 7,
                                isLearning = true
                            ), LearningVisualState.NotSelected
                        ),
                        VerbModelUiState(
                            VerbModel(
                                id = 3,
                                infinitive = "be",
                                pastSimple = "was/were",
                                pastParticipate = "been",
                                frequency = "high",
                                pattern = "ABC",
                                translation = "быть",
                                answersGiven = 3,
                                rightAnswers = 1,
                                isLearning = false
                            ), LearningVisualState.JustAdded
                        ),
                        VerbModelUiState(
                            VerbModel(
                                id = 4,
                                infinitive = "understand",
                                pastSimple = "understood",
                                pastParticipate = "understood",
                                frequency = "low",
                                pattern = "ABB",
                                translation = "понимать",
                                answersGiven = 0,
                                rightAnswers = 0,
                                isLearning = false
                            ), LearningVisualState.JustRemoved
                        )
                    ).toImmutableList()
                ),
                onBackClick = {},
                onSaveClick = {},
                onVerbClick = {},
                onHideVerbsListScreenHint = { },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(dp16),
                onQueryChange = { }
            )
        }
    }
}

