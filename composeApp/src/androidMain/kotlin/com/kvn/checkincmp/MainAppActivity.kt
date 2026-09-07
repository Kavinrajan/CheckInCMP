package com.kvn.checkincmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
            startKoin {
                androidLogger()
                androidContext(this@MainAppActivity)
                modules(appModule)
            }
            setContent {
                App()
            }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}