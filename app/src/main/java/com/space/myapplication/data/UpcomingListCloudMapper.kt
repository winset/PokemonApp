package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface UpcomingListCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<Abstract.Object<Upcoming, ToUpcomingMapper>>): List<Upcoming>

    class Base(private val toUpcomingMapper: ToUpcomingMapper) : UpcomingListCloudMapper {
        override fun map(cloudList: List<Abstract.Object<Upcoming, ToUpcomingMapper>>) = cloudList.map { upcomingCloud ->
            upcomingCloud.map(toUpcomingMapper)
        }
    }
}