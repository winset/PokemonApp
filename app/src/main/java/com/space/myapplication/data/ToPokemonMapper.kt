package com.space.myapplication.data

import com.space.myapplication.core.Abstract

interface ToPokemonMapper : Abstract.Mapper {
    fun map(capsule_id: String, status: String): PokemonData

    class Base() : ToPokemonMapper {
        override fun map(capsule_id: String, status: String) = PokemonData(capsule_id, status)
    }
}
