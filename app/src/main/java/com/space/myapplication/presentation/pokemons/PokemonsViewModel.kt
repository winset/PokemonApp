package com.space.myapplication.presentation.pokemons

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.presentation.MainCommunication
import com.space.myapplication.presentation.Navigator
import com.space.myapplication.presentation.Screen
import com.space.myapplication.presentation.pokemons.PokemonCommunication
import com.space.myapplication.presentation.pokemons.PokemonUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonsViewModel(
    private val interactor: PokemonsInteractor,
    private val uiMapper: PokemonsDomainToUiMapper,
    private val communication: PokemonCommunication,
    private val navigator: Navigator,
    private val mainCommunication: MainCommunication
) : ViewModel() {

    private var isLoading = false
    private var page = 0

    fun init() {
        navigator.save(Screen.POKEMONS_SCREEN)
    }

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

    fun onPokemonClick(name: String) {
       // navigator.save(Screen.SPECIES_SCREEN)
        mainCommunication.map(Screen.SPECIES_SCREEN)
    }
}