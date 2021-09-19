package com.space.myapplication.data.net

import com.google.gson.annotations.SerializedName
import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.ToUpcomingMapper

data class UpcomingDto(
   /* @SerializedName("capsule_serial")
    private val capsule_serial: String,*/
    @SerializedName("capsule_id")
    private val capsule_id: String,
    @SerializedName("status")
    private val status: String,
   /* @SerializedName("original_launch")
    private val original_launch: String,
    @SerializedName("original_launch_unix")
    private val original_launch_unix: String,
    @SerializedName("missions")
    private val missions: List<String>,
    @SerializedName("landings")
    private val landings: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("details")
    private val details: String,
    @SerializedName("reuse_count")
    private val reuse_count: Int*/
) : Abstract.Object<Upcoming, ToUpcomingMapper> {
    override fun map(mapper: ToUpcomingMapper) = mapper.map(capsule_id, status)
}
