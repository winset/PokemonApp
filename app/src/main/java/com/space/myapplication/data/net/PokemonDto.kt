package com.space.myapplication.data.net

import com.google.gson.annotations.SerializedName
import com.space.myapplication.core.Abstract
import com.space.myapplication.data.PokemonData
import com.space.myapplication.data.ToPokemonMapper

data class PokemonDto(
    @SerializedName("name")
    private val name: String,
    @SerializedName("url")
    private val url: String
) : Abstract.Object<PokemonData, ToPokemonMapper> {
    override fun map(mapper: ToPokemonMapper) = mapper.map(name, url)
}
