package com.azhel.gogonedemo.designsystem.uielements

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.azhel.gogonedemo.designsystem.dp12
import com.azhel.gogonedemo.designsystem.dp2

@Composable
fun AppListItemCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dp12),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = dp2),
        content = content
    )
}