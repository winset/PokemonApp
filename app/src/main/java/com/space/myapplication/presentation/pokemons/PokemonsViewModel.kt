package com.space.myapplication.presentation.pokemons

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonsViewModel @Inject constructor(
    private val interactor: PokemonsInteractor,
    private val uiMapper: PokemonsDomainToUiMapper<PokemonsUi>,
    private val communication: PokemonCommunication
) : ViewModel() {

    private var isLoading = false
    private var page = 0

    fun getPokemons() {
        if (!isLoading) {
            isLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = interactor.getPokemons(page)
                val pokemonsUi = result.map(uiMapper)
                withContext(Dispatchers.Main) {
                    pokemonsUi.map(communication)
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