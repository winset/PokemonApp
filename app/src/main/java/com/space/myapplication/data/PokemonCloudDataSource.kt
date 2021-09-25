package com.space.myapplication.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.space.myapplication.data.net.PokemonDto
import com.space.myapplication.data.net.UpcomingService

interface PokemonCloudDataSource {
    suspend fun getUpcoming(): List<PokemonDto>

    class Base(private val service: UpcomingService,
    private val gson: Gson
               ) : PokemonCloudDataSource {

        private val type = object :TypeToken<List<PokemonDto>>(){}.type
        override suspend fun getUpcoming(): List<PokemonDto> {
            return service.getUpcoming().results
        }
    }
}