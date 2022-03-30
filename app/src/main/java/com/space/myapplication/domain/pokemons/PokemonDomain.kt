package com.space.myapplication.domain.pokemons

data class PokemonDomain(
    private val name: String,
    private val url: String
) {
    fun <T> map(mapper: PokemonDomainToUiMapper<T>) = mapper.map(name, url)
}
