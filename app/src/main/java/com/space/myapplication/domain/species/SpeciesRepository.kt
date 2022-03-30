package com.space.myapplication.domain.species

interface SpeciesRepository<T> {
    suspend fun getSpecies(name:String): T
}