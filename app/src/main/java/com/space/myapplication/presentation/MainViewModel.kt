package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.core.Upcoming
import com.space.myapplication.domain.UpcomingsInteractor
import com.space.myapplication.domain.UpcomingsDomainToUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: UpcomingsInteractor,
    private val uiMapper: UpcomingsDomainToUiMapper,
    private val communication: UpcomingCommunication
) : ViewModel() {

    fun getUpcomings() {
        communication.map(listOf(UpcomingUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getUpcomings()
            val upcomingUi = result.map(uiMapper)
            withContext(Dispatchers.Main) {
                upcomingUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<UpcomingUi>>) {
        communication.observe(owner, observer)
    }
}