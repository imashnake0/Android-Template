package com.imashnake.template.data.di

import com.imashnake.template.data.HotelsRepository
import com.imashnake.template.data.HotelsRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val networkModule = module {
    single<HotelsRepositoryImpl>() bind HotelsRepository::class
}
