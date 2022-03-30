package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract

interface ToPokemonMapper : Abstract.Mapper {
    fun map(name: String, url: String): PokemonData

    class Base : ToPokemonMapper {
        override fun map(name: String, url: String) = PokemonData.Base(name, url)
    }
}
