package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.ErrorType
import com.space.myapplication.presentation.pokemons.PokemonsUi

interface PokemonsDomainToUiMapper : Abstract.Mapper {
    fun map(pokemons: List<PokemonDomain>): PokemonsUi
    fun map(errorType: ErrorType): PokemonsUi
}