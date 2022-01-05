package com.space.myapplication.data.pokemons.cache

import com.space.myapplication.core.DbWrapper
import com.space.myapplication.core.RealmProvider
import com.space.myapplication.data.pokemons.PokemonData
import io.realm.Realm

interface PokemonCacheDataSource {
    fun getPokemonList(page: Int): List<PokemonEntity>
    fun savePokemonList(pokemonsData: List<PokemonData>, page: Int)

    class Base(
        private val realmProvider: RealmProvider,
        private val pokemonDataToDbMapper: PokemonDataToDbMapper
    ) : PokemonCacheDataSource {
        override fun getPokemonList(page: Int): List<PokemonEntity> {
            realmProvider.provide().use { realm ->
                val pokemonsEntities =
                    realm.where(PokemonEntity::class.java).equalTo("page", page).findAll()
                        ?: emptyList()
                return realm.copyFromRealm(pokemonsEntities)
            }
        }

        override fun savePokemonList(pokemonsData: List<PokemonData>, page: Int) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    pokemonsData.forEach { pokemonData ->
                        pokemonData.mapTo(pokemonDataToDbMapper, PokemonDbWrapper(it), page)
                    }
                }
            }

        private inner class PokemonDbWrapper(realm: Realm) :
            DbWrapper.Base<PokemonEntity>(realm) {
            override fun dbClass(): Class<PokemonEntity> = PokemonEntity::class.java
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
