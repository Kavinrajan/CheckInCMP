package com.kvn.checkincmp.di

import android.content.Context
import com.kvn.data.datasource.createDataStore
import com.kvn.data.datasource.dataStoreFileName
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single<String> { "http://10.0.2.2:8080" }

    single {
        createDataStore(
            producerPath = {
                get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
            }
        )
    }

}