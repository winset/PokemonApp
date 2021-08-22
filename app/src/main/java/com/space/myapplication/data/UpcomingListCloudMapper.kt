package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingDtoMapper

interface UpcomingListCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<UpcomingDto>): List<Upcoming>

    class Base(private val upcomingDtoMapper: UpcomingDtoMapper) : UpcomingListCloudMapper {
        override fun map(cloudList: List<UpcomingDto>) = cloudList.map { upcomingCloud ->
            upcomingCloud.map(upcomingDtoMapper)
        }
    }
}