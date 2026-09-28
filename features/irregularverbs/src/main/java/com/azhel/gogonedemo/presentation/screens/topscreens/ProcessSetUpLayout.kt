package com.azhel.gogonedemo.presentation.screens.topscreens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.designsystem.GoGoneDemoTheme
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.dp32
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons.ArrowDown
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons.ArrowUp
import com.azhel.gogonedemo.designsystem.uielements.AppListItemCard
import com.azhel.gogonedemo.designsystem.uielements.InfoCard
import com.azhel.gogonedemo.presentation.InnerRoutes
import com.azhel.gogonedemo.presentation.LearnVerbsGroup
import com.azhel.gogonedemo.presentation.icons.FeatureIcons
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlin.collections.forEach

@Composable
internal fun ProcessSetUpLayout(
    title: String,
    descriptionPair: Pair<String, () -> Unit>? = null,
    options: List<SetupOption>,
    onAction: (InnerRoutes) -> Unit,
    onGroupClick: (LearnVerbsGroup) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(dp12)) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(
                bottom = dp16
            )
        )

        descriptionPair?.let {
            InfoCard(text = it.first, onCloseClick = it.second)
        }

        Spacer(
            modifier = Modifier.height(dp12)
        )

        LazyColumn {
            items(options) { option ->
                SetupOptionItem(
                    option = option,
                    onAction = onAction,
                    onGroupItemClick = onGroupClick
                )

            }
        }
    }
}

@Composable
private fun SetupOptionItem(
    option: SetupOption,
    onAction: (InnerRoutes) -> Unit,
    onGroupItemClick: (LearnVerbsGroup) -> Unit,
    modifier: Modifier = Modifier
) {
    when (option) {

        is SetupOption.Action -> {
            LearningModeCard(
                option = option,
                modifier = modifier.padding(bottom = dp12),
                onClick = {
                    onAction(option.innerRoutes)
                }
            )
        }

        is SetupOption.Group -> {


            LearningModeCard(
                option = option,
                modifier = modifier.padding(bottom = dp12),
                expanded = option.isExpanded,
                onClick = {
                    onGroupItemClick(option.groupId)
                }
            )

            AnimatedVisibility(
                visible = option.isExpanded
            ) {
                Column {
                    option.children.forEach { child ->
                        SetupOptionItem(
                            option = child,
                            onAction = onAction,
                            onGroupItemClick = onGroupItemClick,
                            modifier = Modifier.padding(
                                start = dp16
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LearningModeCard(
    option: SetupOption,
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onClick: () -> Unit
) {
    val isEmpty = option is SetupOption.Action && option.verbsCount == 0

    AppListItemCard(
        modifier = modifier
            .clickable(
                enabled = !isEmpty,
                onClick = onClick
            )
    ) {
        Column(Modifier.padding(dp16)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = option.icon,
                    contentDescription = stringResource(option.title),
                    modifier = Modifier.size(dp32),
                    tint = if (isEmpty) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    } else {
                        MaterialTheme.colorScheme.primary
                    }
                )

                Spacer(modifier = Modifier.width(dp16))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(option.title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isEmpty) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )

                    Text(
                        text = stringResource(option.description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isEmpty) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }

                if (option is SetupOption.Group) {
                    Icon(
                        imageVector = if (expanded) {
                            ArrowUp
                        } else {
                            ArrowDown
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (option is SetupOption.Action && option.verbsCount != null) {
                Text(
                    modifier = Modifier.padding(top = dp12),
                    text = if (isEmpty) {
                        stringResource(R.string.there_is_no_verbs)
                    } else {
                        stringResource(R.string.verbs_count_in_this_list, option.verbsCount)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isEmpty) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ProcessSetUpLayoutPreview() {
    GoGoneDemoTheme {
        Scaffold { innerPadding ->
            ProcessSetUpLayout(
                title = "title",
                descriptionPair = Pair("description", {}),
                options = listOf(    SetupOption.Action(
                    title = R.string.learning_mode_random_title,
                    description = R.string.learning_mode_random_desc,
                    icon = FeatureIcons.Shuffle,
                    innerRoutes = InnerRoutes.VerbsListToLearn,
                    verbsCount = null
                ),
                    SetupOption.Group(
                        title = R.string.your_verbs,
                        description = R.string.your_verbs_description,
                        icon = FeatureIcons.Collection,
                        isExpanded = false,
                        groupId = LearnVerbsGroup.YOUR_VERBS,
                        children = listOf(

                            SetupOption.Action(
                                title = R.string.your_verbs_all,
                                description = R.string.your_verbs_all_description,
                                icon = AppIcons.List,
                                innerRoutes = InnerRoutes.VerbsListToLearn,
                                verbsCount = 2
                            ),

                            SetupOption.Action(
                                title = R.string.your_verbs_not_tested,
                                description = R.string.your_verbs_not_tested_description,
                                icon = FeatureIcons.Unlearned,
                                innerRoutes = InnerRoutes.VerbsListToLearn,
                                verbsCount = 0
                            ),

                            SetupOption.Action(
                                title = R.string.your_verbs_need_practice,
                                description = R.string.your_verbs_need_practice_description,
                                icon = FeatureIcons.Practice,
                                innerRoutes = InnerRoutes.VerbsListToLearn,
                                verbsCount = null
                            ),

                            SetupOption.Action(
                                title = R.string.your_verbs_learned,
                                description = R.string.your_verbs_learned_description,
                                icon = FeatureIcons.Learned,
                                innerRoutes = InnerRoutes.VerbsListToLearn,
                                verbsCount = 10000000
                            )
                        ).toImmutableList()
                    ),
                ),
                onAction = {  },
                onGroupClick = {  },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(dp16)
            )
        }
    }

}

internal sealed interface SetupOption {

    @get:StringRes
    val title: Int

    @get:StringRes
    val description: Int

    val icon: ImageVector

    data class Action(
        override val title: Int,
        override val description: Int,
        override val icon: ImageVector,
        val innerRoutes: InnerRoutes,
        val verbsCount: Int?
    ) : SetupOption

    data class Group(
        override val title: Int,
        override val description: Int,
        override val icon: ImageVector,
        val children: ImmutableList<SetupOption>,
        val isExpanded: Boolean,
        val groupId: LearnVerbsGroup
    ) : SetupOption
}
