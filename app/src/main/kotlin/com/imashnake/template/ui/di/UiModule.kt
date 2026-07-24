package com.imashnake.template.ui.di

import com.imashnake.template.ui.hotels.HotelsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val uiModule = module {
    viewModel<HotelsViewModel>()
}
