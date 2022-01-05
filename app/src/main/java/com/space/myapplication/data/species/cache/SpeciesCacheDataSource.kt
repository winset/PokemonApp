package com.space.myapplication.data.species.cache

import com.space.myapplication.core.DbWrapper
import com.space.myapplication.core.RealmProvider
import com.space.myapplication.data.species.SpeciesData
import io.realm.Realm

interface SpeciesCacheDataSource {
    fun getSpecies(name: String): SpeciesEntity?
    fun saveSpecies(speciesData: SpeciesData.Success)

    class Base(
        private val realmProvider: RealmProvider,
        private val pokemonDataToDbMapper: SpeciesDataToDbMapper
    ) : SpeciesCacheDataSource {

        override fun getSpecies(name: String): SpeciesEntity? {
            realmProvider.provide().use { realm ->
                val pokemonsEntities =
                    realm.where(SpeciesEntity::class.java).equalTo("name", name).findFirst()
                return if (pokemonsEntities == null) null
                else realm.copyFromRealm(pokemonsEntities)
            }
        }

        override fun saveSpecies(speciesData: SpeciesData.Success) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    speciesData.mapTo(pokemonDataToDbMapper, SpeciesDbWrapper(it))
                }
            }

        private inner class SpeciesDbWrapper(realm: Realm) : DbWrapper.Base<SpeciesEntity>(realm) {
            override fun dbClass(): Class<SpeciesEntity> = SpeciesEntity::class.java
        }
    }
}