package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.PokemonData
import com.space.myapplication.data.ToPokemonMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PokemonEntity : RealmObject(), Abstract.Object<PokemonData, ToPokemonMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var url: String = ""
    var page: Int = 0

    override fun map(mapper: ToPokemonMapper) = PokemonData(name, url)
}
