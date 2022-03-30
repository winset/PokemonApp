package com.space.myapplication.data.pokemons.cloud

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.ToPokemonMapper

interface PokemonsCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<Abstract.Object<PokemonData, ToPokemonMapper>>): List<PokemonData>

    class Base(private val toPokemonMapper: ToPokemonMapper) : PokemonsCloudMapper {
        override fun map(cloudList: List<Abstract.Object<PokemonData, ToPokemonMapper>>) = cloudList.map { upcomingCloud ->
            upcomingCloud.map(toPokemonMapper)
        }
    }
}