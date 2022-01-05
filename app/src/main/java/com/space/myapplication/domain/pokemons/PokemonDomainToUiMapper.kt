package com.space.myapplication.domain.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.PokemonUi

interface PokemonDomainToUiMapper:Abstract.Mapper {
    fun map(name:String,url:String):PokemonUi
}
