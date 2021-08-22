package com.space.myapplication.data.cache

import com.space.myapplication.core.Upcoming

interface UpcomingListCacheMapper {
    fun map(upcomingEntityList: List<UpcomingEntity>): List<Upcoming>

    class Base(private val upcomingCacheMapper: UpcomingCacheMapper) : UpcomingListCacheMapper {
        override fun map(upcomingEntityList: List<UpcomingEntity>) =
            upcomingEntityList.map { upcomingEntity ->
                upcomingEntity.map(upcomingCacheMapper)
            }
    }
}