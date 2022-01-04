package com.space.myapplication.data.pokemons.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.pokemons.PokemonData
import com.space.myapplication.data.pokemons.ToPokemonMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PokemonEntity : RealmObject(), Abstract.Object<PokemonData, ToPokemonMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var url: String = ""
    var page: Int = 0

    override fun map(mapper: ToPokemonMapper) = mapper.map(name, url)
}
