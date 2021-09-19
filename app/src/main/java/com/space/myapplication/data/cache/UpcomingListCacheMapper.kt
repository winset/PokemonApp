package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.ToUpcomingMapper

interface UpcomingListCacheMapper {
    fun map(upcomingEntityList: List<Abstract.Object<Upcoming, ToUpcomingMapper>>): List<Upcoming>

    class Base(private val upcomingCacheMapper: ToUpcomingMapper) : UpcomingListCacheMapper {
        override fun map(upcomingEntityList: List<Abstract.Object<Upcoming, ToUpcomingMapper>>) =
            upcomingEntityList.map { upcomingEntity ->
                upcomingEntity.map(upcomingCacheMapper)
            }
    }
}