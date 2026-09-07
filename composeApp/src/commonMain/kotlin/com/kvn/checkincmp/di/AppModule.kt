package com.kvn.checkincmp.di

import com.kvn.data.di.dataModule
import com.kvn.domain.di.domainModule
import com.kvn.presentation.di.presentationModule
import org.koin.dsl.module

val appModule = listOf(
    platformModule(), presentationModule, domainModule, dataModule
)

expect fun platformModule(): org.koin.core.module.Module