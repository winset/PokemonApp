package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract

sealed class PokemonsData : Abstract.DataObject {

    abstract fun <T> map(mapper: PokemonsDataToDomainMapper<T>): T

    data class Success(private val pokemons: List<PokemonData>) : PokemonsData() {
        override fun <T> map(mapper: PokemonsDataToDomainMapper<T>): T {
            return mapper.map(pokemons)
        }
    }

    data class Fail(private val exception: Exception) : PokemonsData() {
        override fun <T> map(mapper: PokemonsDataToDomainMapper<T>): T {
            return mapper.map(exception)
        }
    }
}