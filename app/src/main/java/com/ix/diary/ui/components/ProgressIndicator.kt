package com.ix.diary.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ix.diary.R
import com.ix.diary.ui.theme.DiaryTheme

@Composable
fun ProgressIndicator() {
    val contentDesc = stringResource(id = R.string.content_desc_loading)
    CircularProgressIndicator(
        modifier = Modifier
            .width(32.dp)
            .semantics {
                contentDescription = contentDesc
            },
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    DiaryTheme {
        ProgressIndicator()
    }
}
