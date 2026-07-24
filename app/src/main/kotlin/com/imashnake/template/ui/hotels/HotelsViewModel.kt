package com.imashnake.template.ui.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imashnake.template.data.HotelsRepository
import com.imashnake.template.data.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate

class HotelsViewModel(
    repository: HotelsRepository
) : ViewModel() {

    val date = LocalDate.now().let { now ->
        "${now.year}-${now.monthValue}-${now.dayOfMonth}"
    }

    val hotels = repository.getHotels().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 500L),
        initialValue = Resource.Loading()
    )
}
