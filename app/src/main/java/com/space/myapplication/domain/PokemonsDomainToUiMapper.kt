package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.PokemonsUi

interface PokemonsDomainToUiMapper : Abstract.Mapper {
    fun map(pokemons: List<PokemonDomain>): PokemonsUi
    fun map(errorType: ErrorType): PokemonsUi
}