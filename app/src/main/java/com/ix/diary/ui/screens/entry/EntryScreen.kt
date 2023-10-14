package com.ix.diary.ui.screens.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.ix.diary.R
import com.ix.diary.ui.theme.DiaryTheme
import com.ix.diary.ui.theme.spacing
import com.ix.diary.viewmodel.EntryEvent
import com.ix.diary.viewmodel.EntryViewModel

@Composable
fun EntryScreen(
    viewModel: EntryViewModel = EntryViewModel(),
    navigateBack: () -> Unit,
) {
    val screenContentDesc = stringResource(id = R.string.label_diary_screen)
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()

//    DisposableEffect(lifecycleOwner) {
//        val reloadWhenResumed = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME) {
//                viewModel.onEvent(event = MainEvent.Init)
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(reloadWhenResumed)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(reloadWhenResumed)
//        }
//    }

    Scaffold() { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .semantics {
                    contentDescription = screenContentDesc
                },
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.m),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(EntryEvent.Cancel)
                        navigateBack()
                    },
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        viewModel.onEvent(EntryEvent.Save)
                        navigateBack()
                    },
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    DiaryTheme {
        EntryScreen(EntryViewModel()) { }
    }
}
