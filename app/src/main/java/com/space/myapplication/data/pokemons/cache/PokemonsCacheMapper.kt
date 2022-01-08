package com.space.myapplication.data.pokemons.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.ToPokemonMapper

interface PokemonsCacheMapper :Abstract.Mapper.Data<List<Abstract.Object<PokemonData, ToPokemonMapper>>, List<PokemonData>>{
    class Base(private val pokemonCacheMapper: ToPokemonMapper) : PokemonsCacheMapper {
        override fun map(data: List<Abstract.Object<PokemonData, ToPokemonMapper>>) =
            data.map { upcomingEntity ->
                upcomingEntity.map(pokemonCacheMapper)
            }
    }
}