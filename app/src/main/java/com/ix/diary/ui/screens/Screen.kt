package com.ix.diary.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.ix.diary.R
import com.ix.diary.ui.theme.DiaryTheme
import com.ix.diary.viewmodel.MainEvent
import com.ix.diary.viewmodel.MainViewModel

@Composable
fun Screen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val screenContentDesc = stringResource(id = R.string.label_screen_1)
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()

    DisposableEffect(lifecycleOwner) {
        val reloadWhenResumed = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onEvent(event = MainEvent.Init)
            }
        }
        lifecycleOwner.lifecycle.addObserver(reloadWhenResumed)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(reloadWhenResumed)
        }
    }

    Text(
        text = "First Screen. Loading: ${state.isLoading}",
        modifier = Modifier
            .semantics {
                contentDescription = screenContentDesc
            },
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    DiaryTheme {
        Screen()
    }
}
