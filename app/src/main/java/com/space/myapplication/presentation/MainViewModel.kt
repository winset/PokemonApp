package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.PokemonsInteractor
import com.space.myapplication.domain.PokemonsDomainToUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: PokemonsInteractor,
    private val uiMapper: PokemonsDomainToUiMapper,
    private val communication: PokemonCommunication
) : ViewModel() {

    var isLoading = false

    fun getPokemons() {
        isLoading = true
        communication.map(listOf(PokemonUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            val result = interactor.getPokemons()
            val upcomingUi = result.map(uiMapper)
            withContext(Dispatchers.Main) {
                upcomingUi.map(communication)
                isLoading = false
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>) {
        communication.observe(owner, observer)
    }
}