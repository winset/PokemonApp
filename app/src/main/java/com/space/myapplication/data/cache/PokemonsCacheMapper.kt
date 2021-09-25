package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.PokemonData
import com.space.myapplication.data.ToPokemonMapper

interface PokemonsCacheMapper {
    fun map(pokemonEntityList: List<Abstract.Object<PokemonData, ToPokemonMapper>>): List<PokemonData>

    class Base(private val pokemonCacheMapper: ToPokemonMapper) : PokemonsCacheMapper {
        override fun map(pokemonEntityList: List<Abstract.Object<PokemonData, ToPokemonMapper>>) =
            pokemonEntityList.map { upcomingEntity ->
                upcomingEntity.map(pokemonCacheMapper)
            }
    }
}