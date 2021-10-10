package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.PokemonsDomain
import java.lang.Exception

interface PokemonsDataToDomainMapper : Abstract.Mapper {
    fun map(pokemons: List<PokemonData>): PokemonsDomain
    fun map(exception: Exception): PokemonsDomain
}