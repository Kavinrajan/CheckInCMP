package com.kvn.checkincmp

import androidx.compose.ui.window.ComposeUIViewController
import com.kvn.checkincmp.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

private var isKoinStarted = false

fun initKoin() {
    if (!isKoinStarted) {
        startKoin {
            modules(appModule)
        }
        isKoinStarted = true
    }
}

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}
