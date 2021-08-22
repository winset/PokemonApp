package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface UpcomingCacheMapper : Abstract.Mapper {
    fun map(upcomingEntity: UpcomingEntity): Upcoming

    class Base : UpcomingCacheMapper {
        override fun map(upcomingEntity: UpcomingEntity) =
            Upcoming(upcomingEntity.capsule_id, upcomingEntity.status)
    }
}
