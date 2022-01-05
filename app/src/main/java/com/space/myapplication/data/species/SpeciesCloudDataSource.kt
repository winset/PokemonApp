package com.space.myapplication.data.species

import com.space.myapplication.data.species.cloud.SpeciesDto
import com.space.myapplication.data.species.cloud.SpeciesService

interface SpeciesCloudDataSource {
    suspend fun getSpecies(name: String): SpeciesDto

    class Base(private val service: SpeciesService) : SpeciesCloudDataSource {
        override suspend fun getSpecies(name: String) = service.getSpecies(name)
    }
}