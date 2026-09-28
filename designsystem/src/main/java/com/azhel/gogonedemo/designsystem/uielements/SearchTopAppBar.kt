package com.azhel.gogonedemo.designsystem.uielements

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.azhel.gogonedemo.designsystem.dp4
import com.azhel.gogonedemo.designsystem.R
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons.ArrowBack

@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = dp4
) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearchActive) {
        if (isSearchActive) {
            focusRequester.requestFocus()
        }
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor,
        tonalElevation = elevation,
        shadowElevation = elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = ArrowBack,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(dp8))

            AnimatedContent(
                targetState = isSearchActive,
                modifier = Modifier.weight(1f)
            ) { searchActive ->

                if (searchActive) {
                    TextField(
                        value = query,
                        onValueChange = onQueryChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(stringResource(R.string.search))
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    isSearchActive = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                } else {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            color = contentColor,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = {
                                isSearchActive = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}