package com.space.myapplication.data.cache

import com.space.myapplication.data.PokemonData

interface PokemonCacheDataSource {
    fun getPokemonList(): List<PokemonEntity>
    fun savePokemonList(pokemonsData: List<PokemonData>)

    class Base(private val realmProvider: RealmProvider,
    private val pokemonDataToDbMapper: PokemonDataToDbMapper) : PokemonCacheDataSource {
        override fun getPokemonList(): List<PokemonEntity> {
            realmProvider.provide().use { realm ->
                val pokemonsEntities =
                    realm.where(PokemonEntity::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(pokemonsEntities)
            }
        }

        override fun savePokemonList(pokemonsData: List<PokemonData>) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    pokemonsData.forEach { pokemonData ->
                        pokemonData.mapTo(pokemonDataToDbMapper,it)
                    }
                }
            }
    }
}

/*
interface DataSource<T> {
    fun getData(): T
}

interface MutableDataSource<R> {
    fun save(data: R)
}

interface CacheDataSource<T, R> : DataSource<T>, MutableDataSource<R>
interface CloudDataSource<E> : DataSource<E>
interface UpcomingCacheDataSourceNew : CacheDataSource<List<UpcomingEntity>, List<Upcoming>>
*/