package com.space.myapplication.domain.pokemons

interface PokemonsInteractor {
    suspend fun getPokemons(page:Int): PokemonsDomain

    class Base(
        private val pokemonRepository: PokemonRepository<PokemonsDomain>,
    ) : PokemonsInteractor {
        override suspend fun getPokemons(page:Int) = pokemonRepository.getPokemon(page)
    }
}