package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.cache.PokemonDataToDbMapper
import com.space.myapplication.data.cache.PokemonEntity
import com.space.myapplication.domain.PokemonDomain
import io.realm.Realm

data class PokemonData(
    private val name: String,
    private val url: String
) : ToPokemonDb<PokemonEntity, PokemonDataToDbMapper>,
    Abstract.Object<PokemonDomain, PokemonDataToDomainMapper> {
    override fun map(mapper: PokemonDataToDomainMapper) = mapper.map(name, url)
    override fun mapTo(mapper: PokemonDataToDbMapper, realm: Realm) = mapper.mapToDB(name, url, realm)
}

interface ToPokemonDb<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, realm: Realm): T
}

