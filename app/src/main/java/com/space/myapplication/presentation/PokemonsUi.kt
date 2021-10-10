package com.space.myapplication.presentation

import com.space.myapplication.core.Abstract

sealed class PokemonsUi : Abstract.Object<Unit, PokemonCommunication> {

    class Base(
        private val pokemons: List<PokemonUi>
    ) : PokemonsUi() {
        override fun map(mapper: PokemonCommunication) = mapper.map(pokemons)
    }
}