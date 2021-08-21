package com.space.myapplication.data.net

import retrofit2.http.GET

interface UpcomingService {

    @GET("capsules/upcoming")
    suspend fun getUpcoming():List<UpcomingDto>

}
/*https://api.spacexdata.com/v3/capsules/upcoming
*/