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
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.VerbsFrequencyEnum
import com.azhel.gogonedemo.domain.VerbsPatternEnum
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.LearnVerbsGroup
import com.azhel.gogonedemo.presentation.icons.FeatureIcons
import com.azhel.gogonedemo.presentation.viewmodels.VerbsListSetUpViewModel
import com.azhel.gogonedemo.presentation.contracts.VerbsListSetUpIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsListSetUpState
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun VerbsListSetUpScreen(modifier: Modifier = Modifier, navigator: Navigator = koinInject()) {

    val context = LocalActivity.current
    BackHandler(enabled = true) {
        context?.finish()
    }

    val viewModel: VerbsListSetUpViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.onIntent(VerbsListSetUpIntent.Load)
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val options = remember(state) {
        createVerbsListSetupOptions(state)
    }

    when {
        state.isLoading -> LoadingContent()
        state.error != null -> ErrorScreen(
            errorText = state.error,
            onRetry = { viewModel.onIntent(VerbsListSetUpIntent.Load) },
            onBack = navigator::removeLast
        )
        else -> {
            val descriptionPair = if (state.isShowVerbsListSetUpScreenHint) {
                Pair(stringResource(R.string.learning_verbs_description), {
                    viewModel.onIntent(VerbsListSetUpIntent.HideVerbsListSetUpScreenHint)
                })
            }
            else null

            ProcessSetUpLayout(
                modifier = modifier,
                options = options,
                title = stringResource(R.string.learning_verbs_title),
                descriptionPair = descriptionPair,
                onAction = navigator::add,
                onGroupClick = { viewModel.onIntent(VerbsListSetUpIntent.ToggleGroup(it))}
            )
        }
    }

}

private fun createVerbsListSetupOptions(state: VerbsListSetUpState) = listOf(

    SetupOption.Group(
        title = R.string.learning_mode_frequency_title,
        description = R.string.learning_mode_frequency_desc,
        icon = FeatureIcons.Frequency,
        isExpanded = state.isFrequencyGroupExpanded,
        groupId = LearnVerbsGroup.FREQUENCY,
        children = listOf(

            SetupOption.Action(
                title = R.string.frequency_high_title,
                description = R.string.frequency_high_desc,
                icon = FeatureIcons.FrequencyHigh,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Frequency(
                    VerbsFrequencyEnum.HIGH
                )),
                verbsCount = state.highUsedVerbsCount
            ),

            SetupOption.Action(
                title = R.string.frequency_medium_title,
                description = R.string.frequency_medium_desc,
                icon = FeatureIcons.FrequencyMedium,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Frequency(
                    VerbsFrequencyEnum.MEDIUM
                )),
                verbsCount = state.midUsedVerbsCount
            ),

            SetupOption.Action(
                title = R.string.frequency_low_title,
                description = R.string.frequency_low_desc,
                icon = FeatureIcons.FrequencyLow,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Frequency(
                    VerbsFrequencyEnum.LOW
                )),
                verbsCount = state.lowUserVerbsCount
            )
        ).toImmutableList()
    ),

    SetupOption.Group(
        title = R.string.learning_mode_pattern_title,
        description = R.string.learning_mode_pattern_desc,
        icon = FeatureIcons.Pattern,
        isExpanded = state.isPatternGroupExpanded,
        groupId = LearnVerbsGroup.PATTERN,
        children = listOf(

            SetupOption.Action(
                title = R.string.pattern_aaa_title,
                description = R.string.pattern_aaa_desc,
                icon = FeatureIcons.PatternAaa,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Pattern(
                    VerbsPatternEnum.AAA
                )),
                verbsCount = state.aaaTypeVerbsCount
            ),

            SetupOption.Action(
                title = R.string.pattern_abb_title,
                description = R.string.pattern_abb_desc,
                icon = FeatureIcons.PatternAbb,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Pattern(
                    VerbsPatternEnum.ABB
                )),
                verbsCount = state.abbTypeVerbsCount
            ),

            SetupOption.Action(
                title = R.string.pattern_aba_title,
                description = R.string.pattern_aba_desc,
                icon = FeatureIcons.PatternAba,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Pattern(
                    VerbsPatternEnum.ABA
                )),
                verbsCount = state.abaTypeVerbsCount
            ),

            SetupOption.Action(
                title = R.string.pattern_abc_title,
                description = R.string.pattern_abc_desc,
                icon = FeatureIcons.PatternAbc,
                innerRoutes = InnerRoutes.VerbsList(ProcessSetup.Pattern(
                    VerbsPatternEnum.ABC
                )),
                verbsCount = state.abcTypeVerbsCount
            )
        ).toImmutableList()
    ),

    SetupOption.Action(
        title = R.string.learning_mode_all_title,
        description = R.string.learning_mode_all_desc,
        icon = FeatureIcons.AllVerbs,
        innerRoutes = InnerRoutes.VerbsList(ProcessSetup.All),
        verbsCount = state.allVerbsCount
    ),

    SetupOption.Action(
        title = R.string.verbs_under_learn,
        description = R.string.verbs_under_learn_description,
        icon = FeatureIcons.VerbsUnderLearn,
        innerRoutes = InnerRoutes.VerbsListToLearn,
        verbsCount = state.verbsUnderLearnCount
    )
)
