package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.PokemonsInteractor
import com.space.myapplication.domain.PokemonsDomainToUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: PokemonsInteractor,
    private val uiMapper: PokemonsDomainToUiMapper,
    private val communication: UpcomingCommunication
) : ViewModel() {

    fun getUpcomings() {
        communication.map(listOf(PokemonUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getUpcomings()
            val upcomingUi = result.map(uiMapper)
            withContext(Dispatchers.Main) {
                upcomingUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>) {
        communication.observe(owner, observer)
    }
}