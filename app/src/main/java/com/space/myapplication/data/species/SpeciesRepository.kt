package com.space.myapplication.data.species

import com.space.myapplication.data.species.cache.SpeciesCacheDataSource

interface SpeciesRepository {
    suspend fun getSpecies(name:String): SpeciesData

    class Base(
        private val speciesCloudDataSource: SpeciesCloudDataSource,
        private val cacheDataSource: SpeciesCacheDataSource,
        private val toSpeciesMapper: ToSpeciesMapper,
    ) : SpeciesRepository {
        override suspend fun getSpecies(name:String): SpeciesData = try {
            val speciesEntity = cacheDataSource.getSpecies(name)
            if (speciesEntity==null) {
                val speciesDto = speciesCloudDataSource.getSpecies(name)
                val speciesData = speciesDto.map(toSpeciesMapper)
                cacheDataSource.saveSpecies(speciesData)
                speciesData
            } else {
                speciesEntity.map(toSpeciesMapper)
            }
        } catch (exception: Exception) {
            SpeciesData.Fail(exception)
        }
    }
}