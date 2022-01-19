package com.space.myapplication.presentation.species

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.Navigator
import com.space.myapplication.presentation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpeciesViewModel(
    private val interactor: SpeciesInteractor,
    private val uiMapper: SpeciesDomainToUiMapper,
    private val communication: SpeciesCommunication,
    private val navigator: Navigator
) : ViewModel() {

    fun init() {
        navigator.save(Screen.SPECIES_SCREEN)
    }

    fun getInfo(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val uiInfo = interactor.getSpecies(name).map(uiMapper)
            communication.map(uiInfo)
        }
    }

}