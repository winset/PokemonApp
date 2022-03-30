package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract

interface PokemonDomainToUiMapper<T> : Abstract.Mapper {
    fun map(name: String, url: String): T
}
