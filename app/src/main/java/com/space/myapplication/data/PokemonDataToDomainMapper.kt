package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.domain.PokemonDomain

interface PokemonDataToDomainMapper:Abstract.Mapper {
    fun map(name:String,url:String):PokemonDomain
}