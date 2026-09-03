package com.kvn.checkincmp

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(viewModel: AppViewModel = koinViewModel()) {
    MaterialTheme {
        val uiSource = viewModel.state.collectAsState()
        if (!uiSource.value.isLoading) {
            TrevnorNavRoot(uiSource.value.authToken)
        } else {
            CircularProgressIndicator()
        }
    }
}
