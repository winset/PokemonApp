package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.pokemons.PokemonDomain

interface PokemonDataToDomainMapper:Abstract.Mapper {
    fun map(name:String,url:String): PokemonDomain
}