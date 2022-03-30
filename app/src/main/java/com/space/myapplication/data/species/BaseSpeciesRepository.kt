package com.space.myapplication.data.species

import com.space.myapplication.data.species.cache.SpeciesCacheDataSource
import com.space.myapplication.domain.species.SpeciesRepository

class BaseSpeciesRepository<T>(
    private val speciesCloudDataSource: SpeciesCloudDataSource,
    private val cacheDataSource: SpeciesCacheDataSource,
    private val toSpeciesMapper: ToSpeciesMapper,
    private val mapper: SpeciesDataToDomainMapper<T>
) : SpeciesRepository<T> {

    override suspend fun getSpecies(name: String): T {
        val data = try {
            val speciesEntity = cacheDataSource.getSpecies(name)
            if (speciesEntity == null) {
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
        return data.map(mapper)
    }
}