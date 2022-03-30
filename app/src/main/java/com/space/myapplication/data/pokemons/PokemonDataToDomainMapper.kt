package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract

interface PokemonDataToDomainMapper<T> : Abstract.Mapper {
    fun map(name: String, url: String): T
}