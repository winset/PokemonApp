package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.PokemonsDomain

sealed class PokemonsData : Abstract.Object<PokemonsDomain, PokemonsDataToDomainMapper> {
    data class Success(private val pokemons: List<PokemonData>): PokemonsData(){
        override fun map(mapper: PokemonsDataToDomainMapper): PokemonsDomain {
            return mapper.map(pokemons)
        }
    }
    data class Fail(private val exception:Exception): PokemonsData() {
        override fun map(mapper: PokemonsDataToDomainMapper): PokemonsDomain {
            return mapper.map(exception)
        }
    }
}