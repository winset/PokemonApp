package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import io.realm.RealmObject

interface PokemonData : Abstract.DataObject {
    fun <T> map(mapper: PokemonDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: PokemonDataToDbMapper<T>, db: DbWrapper<T>, page: Int): T

    data class Base(
        private val name: String,
        private val url: String
    ) : PokemonData {
        override fun <T> map(mapper: PokemonDataToDomainMapper<T>): T = mapper.map(name, url)
        override fun <T : RealmObject> map(
            mapper: PokemonDataToDbMapper<T>,
            db: DbWrapper<T>,
            page: Int
        ): T = mapper.mapToDB(name, url, page, db)
    }
}
