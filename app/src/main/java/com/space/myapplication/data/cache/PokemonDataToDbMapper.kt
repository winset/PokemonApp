package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import io.realm.Realm

interface PokemonDataToDbMapper : Abstract.Mapper {
    fun mapToDB(name: String, url: String,realm: Realm): PokemonEntity

    class Base:PokemonDataToDbMapper{
        override fun mapToDB(name: String, url: String,realm: Realm): PokemonEntity {
            val maxId: Number? = realm.where(PokemonEntity::class.java).max("id")
            val nextId = if (maxId == null) 1 else maxId.toInt() + 1
            val pokemonEntity = realm.createObject(PokemonEntity::class.java,nextId)
            pokemonEntity.name = name
            pokemonEntity.url = url
            return pokemonEntity
        }
    }
}