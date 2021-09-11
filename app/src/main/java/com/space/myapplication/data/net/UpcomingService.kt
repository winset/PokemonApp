package com.space.myapplication.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface UpcomingService {

    @GET("capsules/upcoming")
    suspend fun getUpcoming():ResponseBody

}
/*https://api.spacexdata.com/v3/capsules/upcoming
*/