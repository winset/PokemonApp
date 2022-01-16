package com.space.myapplication.presentation.species

import androidx.lifecycle.ViewModel
import com.space.myapplication.domain.pokemons.PokemonsDomainToUiMapper
import com.space.myapplication.domain.pokemons.PokemonsInteractor
import com.space.myapplication.domain.species.SpeciesDomainToUiMapper
import com.space.myapplication.domain.species.SpeciesInteractor
import com.space.myapplication.presentation.Navigator
import com.space.myapplication.presentation.Screen

class SpeciesViewModel(
    private val interactor: SpeciesInteractor,
    private val uiMapper: SpeciesDomainToUiMapper,
    private val communication: SpeciesCommunication,
    private val navigator: Navigator
) : ViewModel() {

    fun init() {
        navigator.save(Screen.SPECIES_SCREEN)
    }

}