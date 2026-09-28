package com.azhel.gogonedemo.presentation.screens.topscreens

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.uielements.ErrorScreen
import com.azhel.gogonedemo.designsystem.uielements.LoadingContent
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.LearnVerbsGroup
import com.azhel.gogonedemo.presentation.icons.FeatureIcons
import com.azhel.gogonedemo.presentation.viewmodels.LearnVerbsSetUpViewModel
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsSetUpIntent
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsSetUpState
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun LearnVerbsSetUpScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator = koinInject(),
    viewModel: LearnVerbsSetUpViewModel = koinViewModel()
) {

    val context = LocalActivity.current

    BackHandler(enabled = true) {
        context?.finish()
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.onIntent(LearnVerbsSetUpIntent.Load)
    }

    val listOptions = remember(state) {
        buildList(state)
    }

    when {
        state.isLoading -> LoadingContent()
        state.error != null -> ErrorScreen(
            errorText = state.error,
            onRetry = { viewModel.onIntent(LearnVerbsSetUpIntent.Load) },
            onBack = navigator::removeLast
        )
        else -> ProcessSetUpLayout(
            modifier = modifier,
            options = listOptions,
            title = stringResource(R.string.choose_mode),
            onAction = navigator::add,
            onGroupClick = {
                viewModel.onIntent(LearnVerbsSetUpIntent.ToggleGroup(it))
            }
        )
    }

}

internal fun buildList(state: LearnVerbsSetUpState) = listOf(
    SetupOption.Action(
        title = R.string.learning_mode_random_title,
        description = R.string.learning_mode_random_desc,
        icon = FeatureIcons.Shuffle,
        innerRoutes = goToLearnProcess(ProcessSetup.Number(20)),
        verbsCount = null
    ),
    SetupOption.Group(
        title = R.string.your_verbs,
        description = R.string.your_verbs_description,
        icon = FeatureIcons.Collection,
        isExpanded = state.isYourVerbsExpanded,
        groupId = LearnVerbsGroup.YOUR_VERBS,
        children = listOf(

            SetupOption.Action(
                title = R.string.your_verbs_all,
                description = R.string.your_verbs_all_description,
                icon = AppIcons.List,
                innerRoutes = goToLearnProcess(
                    ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.ALL)
                ),
                verbsCount = state.allVerbsUnderLearnCount
            ),

            SetupOption.Action(
                title = R.string.your_verbs_not_tested,
                description = R.string.your_verbs_not_tested_description,
                icon = FeatureIcons.Unlearned,
                innerRoutes = goToLearnProcess(
                    ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.NOT_TESTED)
                ),
                verbsCount = state.notTestedVerbsUnderLearnCount
            ),

            SetupOption.Action(
                title = R.string.your_verbs_need_practice,
                description = R.string.your_verbs_need_practice_description,
                icon = FeatureIcons.Practice,
                innerRoutes = goToLearnProcess(
                    ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.NEED_PRACTICE)
                ),
                verbsCount = state.needPracticeVerbsUnderLearnCount
            ),

            SetupOption.Action(
                title = R.string.your_verbs_learned,
                description = R.string.your_verbs_learned_description,
                icon = FeatureIcons.Learned,
                innerRoutes = goToLearnProcess(
                    ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.LEARNED)
                ),
                verbsCount = state.learnedVerbsUnderLearnCount
            )
        ).toImmutableList()
    ),
)

private fun goToLearnProcess(processSetup: ProcessSetup): InnerRoutes.LearnProcess {
    return InnerRoutes.LearnProcess(processSetup)
}
