package com.ix.diary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.common.SignInButton
import com.ix.diary.ui.theme.DiaryTheme

@Composable
fun GoogleSignInButton(onClick: () -> Unit = {}) {
    AndroidView(
        modifier = Modifier,
        factory = { context ->
            SignInButton(context).apply {
                setOnClickListener {
                    onClick()
                }
                setSize(SignInButton.SIZE_WIDE)
            }
        },
    )
}

@Preview
@Composable
fun GoogleSignInButtonPreview() {
    DiaryTheme {
        GoogleSignInButton()
    }
}
