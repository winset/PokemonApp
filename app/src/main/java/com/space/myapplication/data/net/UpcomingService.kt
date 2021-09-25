package com.space.myapplication.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingService {

    @GET("pokemon")
    suspend fun getUpcoming(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ):PokemonResponse

}
