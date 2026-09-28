package com.azhel.gogonedemo.presentation.screens.processes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.result.ResultEffect
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.GoGoneDemoTheme
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp14
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.dp32
import com.azhel.gogonedemo.designsystem.dp4
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.uielements.ErrorScreen
import com.azhel.gogonedemo.designsystem.uielements.LoadingContent
import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.BreakTestEvent
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.designsystem.uielements.NavTopAppBar
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.viewmodels.LearnVerbsViewModel
import com.azhel.gogonedemo.presentation.contracts.LearnUserAnswer
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsIntent
import com.azhel.gogonedemo.presentation.contracts.LearningCardType
import com.azhel.gogonedemo.presentation.contracts.VerbLearningState
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
internal fun LearnVerbsScreen(learnProcess: InnerRoutes.LearnProcess) {

    val viewModel = koinViewModel<LearnVerbsViewModel>()
    val navigator: Navigator = koinInject()
    val languageCode = Locale.current.language

    LaunchedEffect(languageCode) {
        viewModel.onIntent(LearnVerbsIntent.Load(local = languageCode, processSetup = learnProcess.processSetup))

    }



    ResultEffect<BreakTestEvent> {
        navigator.removeLast()
    }

    val exitDialogTitleText = stringResource(R.string.learn_exit_sure)
    val state = viewModel.state.collectAsStateWithLifecycle().value

    when {
        state.isLoading -> LoadingContent()
        state.errorMessage != null -> ErrorScreen(
            errorText = state.errorMessage,
            onRetry = { viewModel.onIntent(LearnVerbsIntent.Load(local = languageCode, processSetup = learnProcess.processSetup)) },
            onBack = {
                navigator.add(InnerRoutes.ExitTestSureDialog(exitDialogTitleText))
            }
        )

        state.currentVerb == null -> ErrorScreen(
            errorText = stringResource(R.string.empty_list_error),
            onBack = {
                navigator.add(InnerRoutes.ExitTestSureDialog(exitDialogTitleText))
            }
        )

        else -> LearnVerbsLayout(
            screenState = state,
            onInfinitiveTyping = { viewModel.onIntent(LearnVerbsIntent.TypingInfinitive(it)) },
            onPastSimpleTyping = { viewModel.onIntent(LearnVerbsIntent.TypingPastSimple(it)) },
            onPastParticipleTyping = { viewModel.onIntent(LearnVerbsIntent.TypingPastParticiple(it)) },
            onInfinitiveFocusLost = { viewModel.onIntent(LearnVerbsIntent.FocusLostInfinitive) },
            onPastSimpleFocusLost = { viewModel.onIntent(LearnVerbsIntent.FocusLostPastSimple) },
            onPastParticipleFocusLost = { viewModel.onIntent(LearnVerbsIntent.FocusLostPastParticiple) },
            onNextCard = { viewModel.onIntent(LearnVerbsIntent.NextCard) },
            onPreviousCard = { viewModel.onIntent(LearnVerbsIntent.PreviousCard) },
            onBack = {
                navigator.add(InnerRoutes.ExitTestSureDialog(exitDialogTitleText))
            },
            onFinish = {
                navigator.add(InnerRoutes.LearnResult(learnProcess.processSetup), 1)
            }
        )
    }

}

@Composable
private fun LearnVerbsLayout(
    screenState: VerbLearningState,
    onInfinitiveTyping: (String) -> Unit,
    onPastSimpleTyping: (String) -> Unit,
    onPastParticipleTyping: (String) -> Unit,
    onInfinitiveFocusLost: () -> Unit,
    onPastSimpleFocusLost: () -> Unit,
    onPastParticipleFocusLost: () -> Unit,
    onNextCard: () -> Unit,
    onPreviousCard: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    BackHandler(
        enabled = true,
        onBack = onBack
    )

    Scaffold(
        topBar = {
            NavTopAppBar(
                title = stringResource(R.string.learn_verbs),
                onBackClick = onBack
            )
        }
    ) { innerPadding ->

        LearnVerbsContent(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(dp12),
            screenState = screenState,
            onInfinitiveTyping = onInfinitiveTyping,
            onPastSimpleTyping = onPastSimpleTyping,
            onPastParticipleTyping = onPastParticipleTyping,
            onInfinitiveFocusLost = onInfinitiveFocusLost,
            onPastSimpleFocusLost = onPastSimpleFocusLost,
            onPastParticipleFocusLost = onPastParticipleFocusLost,
            onNextCard = onNextCard,
            onPreviousCard = onPreviousCard,
            onFinish = onFinish
        )
    }
}

@Composable
private fun LearnVerbsContent(
    modifier: Modifier = Modifier,
    screenState: VerbLearningState,
    onInfinitiveTyping: (String) -> Unit,
    onPastSimpleTyping: (String) -> Unit,
    onPastParticipleTyping: (String) -> Unit,
    onInfinitiveFocusLost: () -> Unit,
    onPastSimpleFocusLost: () -> Unit,
    onPastParticipleFocusLost: () -> Unit,
    onNextCard: () -> Unit,
    onPreviousCard: () -> Unit,
    onFinish: () -> Unit
) {
    if (screenState.verbs.isEmpty()) {
        return
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(dp16))

        Progress(
            currentIndex = screenState.currentIndex,
            total = screenState.totalVerbs
        )

        Spacer(modifier = Modifier.height(dp32))

        Text(
            text = screenState.currentVerb?.translation.orEmpty(),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(dp32))

        when (screenState.cardType) {
            LearningCardType.SHOW -> {
                screenState.currentVerb?.let { currentVerb ->
                    ShowCard(
                        verb = currentVerb
                    )
                }

            }

            LearningCardType.PRACTICE -> {
                PracticeCard(
                    state = screenState,
                    onInfinitiveTyping = onInfinitiveTyping,
                    onPastSimpleTyping = onPastSimpleTyping,
                    onPastParticipleTyping = onPastParticipleTyping,
                    onInfinitiveFocusLost = onInfinitiveFocusLost,
                    onPastSimpleFocusLost = onPastSimpleFocusLost,
                    onPastParticipleFocusLost = onPastParticipleFocusLost,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        LearningNavigation(
            state = screenState,
            onPreviousCard = onPreviousCard,
            onNextCard = onNextCard,
            onFinish = onFinish
        )

        Spacer(modifier = Modifier.height(dp16))
    }
}

@Composable
private fun Progress(currentIndex: Int, total: Int) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(
                R.string.verb_progress,
                currentIndex + 1,
                total
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(dp8))

        LinearProgressIndicator(
            progress = {
                (currentIndex + 1).toFloat() / total
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dp4),
            strokeCap = StrokeCap.Round,
        )
    }
}

@Composable
private fun ShowCard(verb: VerbModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dp12)
    ) {

        VerbInfo(stringResource(R.string.verb_pattern), verb.pattern)

        VerbInfo(stringResource(R.string.usage_frequency), verb.frequency,
            Modifier.padding(bottom = dp16))

        VerbFormRow(
            title = stringResource(R.string.infinitive),
            value = verb.infinitive
        )

        VerbFormRow(
            title = stringResource(R.string.past_simple),
            value = verb.pastSimple
        )

        VerbFormRow(
            title = stringResource(R.string.past_participle),
            value = verb.pastParticipate
        )
    }
}

@Composable
private fun VerbInfo(
    title: String,
    type: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = dp16),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = type,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun VerbFormRow(title: String, value: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dp16,
                    vertical = dp14
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PracticeCard(
    state: VerbLearningState,
    onInfinitiveTyping: (String) -> Unit,
    onPastSimpleTyping: (String) -> Unit,
    onPastParticipleTyping: (String) -> Unit,
    onInfinitiveFocusLost: () -> Unit,
    onPastSimpleFocusLost: () -> Unit,
    onPastParticipleFocusLost: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dp12),
    ) {

        VerbAnswerField(
            value = state.answer.infinitive,
            label = stringResource(R.string.infinitive),
            isCorrect = state.infinitiveCorrect,
            onValueChange = onInfinitiveTyping,
            onFocusLost = onInfinitiveFocusLost,
            imeAction = ImeAction.Next,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

        VerbAnswerField(
            value = state.answer.pastSimple,
            label = stringResource(R.string.past_simple),
            isCorrect = state.pastSimpleCorrect,
            onValueChange = onPastSimpleTyping,
            onFocusLost = onPastSimpleFocusLost,
            imeAction = ImeAction.Next,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

        VerbAnswerField(
            value = state.answer.pastParticipate,
            label = stringResource(R.string.past_participle),
            isCorrect = state.pastParticipleCorrect,
            onValueChange = onPastParticipleTyping,
            onFocusLost = onPastParticipleFocusLost,
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
            }
        )
    }
}

@Composable
private fun VerbAnswerField(
    value: String,
    label: String,
    isCorrect: Boolean?,
    onValueChange: (String) -> Unit,
    onFocusLost: () -> Unit,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when (isCorrect) {
        true -> MaterialTheme.colorScheme.primary
        false -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.outline
    }

    val labelColor = when (isCorrect) {
        true -> MaterialTheme.colorScheme.primary
        false -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (isFocused && !focusState.isFocused) {
                    onFocusLost()
                }

                isFocused = focusState.isFocused
            },
        label = {
            Text(
                text = label,
                color = labelColor
            )
        },
        singleLine = true,
        isError = isCorrect == false,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
        ),
        trailingIcon = {
            when (isCorrect) {
                true -> {
                    Icon(
                        imageVector = AppIcons.Checked,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                false -> {
                    Icon(
                        imageVector = AppIcons.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                null -> Unit
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrectEnabled = false,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onImeAction()
            },
            onDone = {
                onImeAction()
            }
        )
    )
}

@Composable
private fun LearningNavigation(
    state: VerbLearningState,
    onPreviousCard: () -> Unit,
    onNextCard: () -> Unit,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        if (state.cardType == LearningCardType.PRACTICE) {

            TextButton(onClick = onPreviousCard) {
                Text(
                    text = stringResource(R.string.learn_again)
                )
            }
            Spacer(modifier = Modifier.height(dp8))
        }

        val onClick = if (state.currentIndex == state.verbs.lastIndex && state.cardType == LearningCardType.PRACTICE) {
            onFinish
        } else {
            onNextCard
        }

        Button(
            onClick = onClick,
            enabled = state.canGoNext,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.continue_learning)
            )
        }

    }
}

private val previewVerbs = listOf(
    VerbModel(
        id = 1,
        infinitive = "go",
        pastSimple = "went",
        pastParticipate = "gone",
        frequency = "high",
        pattern = "ABC",
        translation = "идти",
        answersGiven = 0,
        rightAnswers = 0,
        isLearning = true
    ),
    VerbModel(
        id = 2,
        infinitive = "write",
        pastSimple = "wrote",
        pastParticipate = "written",
        frequency = "medium",
        pattern = "ABC",
        translation = "писать",
        answersGiven = 3,
        rightAnswers = 2,
        isLearning = true
    ),
    VerbModel(
        id = 3,
        infinitive = "have",
        pastSimple = "had",
        pastParticipate = "had",
        frequency = "high",
        pattern = "ABB",
        translation = "иметь",
        answersGiven = 5,
        rightAnswers = 4,
        isLearning = true
    )
).toImmutableList()

@Preview(
    name = "Show verb",
    showBackground = true
)
@Composable
private fun LearnVerbsShowPreview() {
    GoGoneDemoTheme {
        LearnVerbsLayout(
            screenState = VerbLearningState(
                verbs = previewVerbs,
                currentIndex = 0,
                cardType = LearningCardType.SHOW,
                isLoading = false
            ),
            onInfinitiveTyping = {},
            onPastSimpleTyping = {},
            onPastParticipleTyping = {},
            onInfinitiveFocusLost = {},
            onPastSimpleFocusLost = {},
            onPastParticipleFocusLost = {},
            onNextCard = {},
            onPreviousCard = {},
            onBack = {},
            onFinish = {}
        )
    }
}

@Preview(
    name = "Empty answer",
    showBackground = true
)
@Composable
private fun LearnVerbsEmptyAnswerPreview() {
    GoGoneDemoTheme {
        LearnVerbsLayout(
            screenState = VerbLearningState(
                verbs = previewVerbs,
                currentIndex = 0,
                cardType = LearningCardType.PRACTICE,
                answer = LearnUserAnswer(),
                infinitiveCorrect = null,
                pastSimpleCorrect = null,
                pastParticipleCorrect = null,
                canGoNext = false,
                isLoading = false
            ),
            onInfinitiveTyping = {},
            onPastSimpleTyping = {},
            onPastParticipleTyping = {},
            onInfinitiveFocusLost = {},
            onPastSimpleFocusLost = {},
            onPastParticipleFocusLost = {},
            onNextCard = {},
            onPreviousCard = {},
            onBack = {},
            onFinish = {}
        )
    }
}

@Preview(
    name = "Partially answered",
    showBackground = true
)
@Composable
private fun LearnVerbsPartiallyAnsweredPreview() {
    GoGoneDemoTheme {
        LearnVerbsLayout(
            screenState = VerbLearningState(
                verbs = previewVerbs,
                currentIndex = 1,
                cardType = LearningCardType.PRACTICE,
                answer = LearnUserAnswer(
                    infinitive = "write",
                    pastSimple = "writed",
                    pastParticipate = ""
                ),
                infinitiveCorrect = true,
                pastSimpleCorrect = false,
                pastParticipleCorrect = null,
                canGoNext = false,
                isLoading = false
            ),
            onInfinitiveTyping = {},
            onPastSimpleTyping = {},
            onPastParticipleTyping = {},
            onInfinitiveFocusLost = {},
            onPastSimpleFocusLost = {},
            onPastParticipleFocusLost = {},
            onNextCard = {},
            onPreviousCard = {},
            onBack = {},
            onFinish = {}
        )
    }
}

@Preview(
    name = "All answers correct",
    showBackground = true
)
@Composable
private fun LearnVerbsAllCorrectPreview() {
    GoGoneDemoTheme {
        LearnVerbsLayout(
            screenState = VerbLearningState(
                verbs = previewVerbs,
                currentIndex = 0,
                cardType = LearningCardType.PRACTICE,
                answer = LearnUserAnswer(
                    infinitive = "go",
                    pastSimple = "went",
                    pastParticipate = "gone"
                ),
                infinitiveCorrect = true,
                pastSimpleCorrect = true,
                pastParticipleCorrect = true,
                canGoNext = true,
                isLoading = false
            ),
            onInfinitiveTyping = {},
            onPastSimpleTyping = {},
            onPastParticipleTyping = {},
            onInfinitiveFocusLost = {},
            onPastSimpleFocusLost = {},
            onPastParticipleFocusLost = {},
            onNextCard = {},
            onPreviousCard = {},
            onBack = {},
            onFinish = {}
        )
    }
}

@Preview(
    name = "Last verb",
    showBackground = true
)
@Composable
private fun LearnVerbsLastVerbPreview() {
    GoGoneDemoTheme {
        LearnVerbsLayout(
            screenState = VerbLearningState(
                verbs = previewVerbs,
                currentIndex = previewVerbs.lastIndex,
                cardType = LearningCardType.PRACTICE,
                answer = LearnUserAnswer(
                    infinitive = "have",
                    pastSimple = "had",
                    pastParticipate = "had"
                ),
                infinitiveCorrect = true,
                pastSimpleCorrect = true,
                pastParticipleCorrect = true,
                canGoNext = true,
                isLoading = false
            ),
            onInfinitiveTyping = {},
            onPastSimpleTyping = {},
            onPastParticipleTyping = {},
            onInfinitiveFocusLost = {},
            onPastSimpleFocusLost = {},
            onPastParticipleFocusLost = {},
            onNextCard = {},
            onPreviousCard = {},
            onBack = {},
            onFinish = {}
        )
    }
}
