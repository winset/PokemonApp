package com.space.myapplication.data.species.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface SpeciesService {

    @GET("pokemon-species")
    fun getSpecies(@Query("name") name: String): SpeciesDto
}