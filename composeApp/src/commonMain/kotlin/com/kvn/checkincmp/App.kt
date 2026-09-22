package com.kvn.checkincmp

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalInspectionMode
import com.kvn.checkincmp.di.appModule
import com.kvn.checkincmp.navigation.TrevnorNavRoot
import com.kvn.presentation.feature.app.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.mp.KoinPlatform

@Composable
@Preview
fun App(viewModel: AppViewModel? = null) {
    if (LocalInspectionMode.current || KoinPlatform.getKoinOrNull() == null) {
        @Suppress("DEPRECATION")
        KoinApplication(application = {
            modules(appModule)
        }) {
            AppContent(viewModel = viewModel ?: koinViewModel())
        }
    } else {
        AppContent(viewModel = viewModel ?: koinViewModel())
    }
}

@Composable
private fun AppContent(viewModel: AppViewModel) {
    MaterialTheme {
        val uiSource = viewModel.state.collectAsState()
        if (!uiSource.value.isLoading) {
            TrevnorNavRoot(uiSource.value.authToken)
        } else {
            androidx.compose.foundation.layout.Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
