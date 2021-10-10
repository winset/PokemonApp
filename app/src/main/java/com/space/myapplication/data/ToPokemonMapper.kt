package com.space.myapplication.data

import com.space.myapplication.core.Abstract

interface ToPokemonMapper : Abstract.Mapper {
    fun map(name: String, url: String): PokemonData

    class Base : ToPokemonMapper {
        override fun map(name: String, url: String) = PokemonData(name, url)
    }
}
