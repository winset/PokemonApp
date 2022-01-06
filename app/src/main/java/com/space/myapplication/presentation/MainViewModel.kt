package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.presentation.pokemons.PokemonCommunication
import com.space.myapplication.presentation.pokemons.PokemonUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val interactor: PokemonsInteractor,
    private val uiMapper: PokemonsDomainToUiMapper,
    private val communication: PokemonCommunication
) : ViewModel() {

    private var isLoading = false
    private var page = 0

    fun getPokemons() {
        if (!isLoading) {
            isLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.getPokemons(page)
                val upcomingUi = result.map(uiMapper)
                withContext(Dispatchers.Main) {
                    upcomingUi.map(communication)
                    page++
                    isLoading = false
                }
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<PokemonUi>>) {
        communication.observe(owner, observer)
    }
}