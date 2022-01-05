package com.space.myapplication.data.pokemons

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.DbWrapper
import com.space.myapplication.data.pokemons.cache.PokemonDataToDbMapper
import com.space.myapplication.data.pokemons.cache.PokemonEntity
import com.space.myapplication.domain.pokemons.PokemonDomain

data class PokemonData(
    private val name: String,
    private val url: String
) : ToPokemonDb<PokemonEntity, PokemonDataToDbMapper>,
    Abstract.Object<PokemonDomain, PokemonDataToDomainMapper> {
    override fun map(mapper: PokemonDataToDomainMapper) = mapper.map(name, url)
    override fun mapTo(mapper: PokemonDataToDbMapper, dbWrapper: DbWrapper<PokemonEntity>, page: Int) =
        mapper.mapToDB(name, url, page, dbWrapper)
}

interface ToPokemonDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, dbWrapper: DbWrapper<T>, page: Int): T
}

