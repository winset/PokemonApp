package com.space.myapplication.data.pokemons.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper

interface PokemonDataToDbMapper : Abstract.Mapper {
    fun mapToDB(name: String, url: String, page: Int, dbWrapper: DbWrapper<PokemonEntity>): PokemonEntity

    class Base : PokemonDataToDbMapper {
        override fun mapToDB(name: String, url: String, page: Int, dbWrapper: DbWrapper<PokemonEntity>): PokemonEntity {
            val pokemonEntity = dbWrapper.createObjectAutoIncremented("id")
            pokemonEntity.name = name
            pokemonEntity.url = url
            pokemonEntity.page = page
            return pokemonEntity
        }
    }
}