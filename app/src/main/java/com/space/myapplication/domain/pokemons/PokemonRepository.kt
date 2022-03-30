package com.space.myapplication.domain.pokemons

interface PokemonRepository<T> {
    suspend fun getPokemon(page:Int): T
}