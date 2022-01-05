package com.space.myapplication.data.pokemons.cloud

import com.google.gson.annotations.SerializedName
import com.space.myapplication.core.Abstract
import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.ToPokemonMapper

data class PokemonDto(
    @SerializedName("name")
    private val name: String,
    @SerializedName("url")
    private val url: String
) : Abstract.Object<PokemonData, ToPokemonMapper> {
    override fun map(mapper: ToPokemonMapper) = mapper.map(name, getImageUrl())
    private fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}