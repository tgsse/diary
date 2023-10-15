package com.ix.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.ix.authentication.viewmodel.AuthViewModel
import com.ix.authentication.R
import com.ix.ui.components.GoogleSignInButton
import com.ix.ui.components.ProgressIndicator
import com.ix.ui.theme.DiaryTheme
import com.ix.ui.theme.spacing

@Composable
fun AuthenticationScreen(
    navigateToDiary: () -> Unit = {},
    viewModel: AuthViewModel = AuthViewModel(),
) {
    val screenContentDesc = stringResource(id = R.string.label_authentication_screen)
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

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

    fun onSignInClick() {
        navigateToDiary()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    contentDescription = screenContentDesc
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.m),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                if (state.isLoading) {
                    ProgressIndicator()
                } else {
                    GoogleSignInButton(onClick = { onSignInClick() })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    DiaryTheme {
        AuthenticationScreen()
    }
}
