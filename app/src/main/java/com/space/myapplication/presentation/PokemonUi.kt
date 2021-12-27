package com.space.myapplication.presentation

import com.space.myapplication.core.Abstract

sealed class PokemonUi : Abstract.Object<Unit, PokemonUi.StringMapper> {
    override fun map(mapper: StringMapper) = Unit
    open fun same(pokemonUi: PokemonUi) = false
    open fun sameContent(pokemonUi: PokemonUi) = false

    object Progress : PokemonUi()
    class Base(
        private val name: String,
        private val url: String
    ) : PokemonUi() {
        override fun map(mapper: StringMapper) = mapper.map(name, url)
        override fun same(pokemonUi: PokemonUi) = pokemonUi is Base && name == pokemonUi.name
        override fun sameContent(pokemonUi: PokemonUi) = if (pokemonUi is Base) {
            url == pokemonUi.url
        } else false
    }

    data class Fail(private val message: String) : PokemonUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(text: String, url: String) {}
        fun map(text: String) {}
    }
}
