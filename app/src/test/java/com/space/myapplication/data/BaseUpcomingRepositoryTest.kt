package com.space.myapplication.data

import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.cache.UpcomingCacheMapper
import com.space.myapplication.data.cache.UpcomingEntity
import com.space.myapplication.data.net.UpcomingDtoMapper

abstract class BaseUpcomingRepositoryTest {

    protected inner class TestUpcomingDtoMapper : UpcomingDtoMapper {
        override fun map(capsule_id: String, status: String) = Upcoming(capsule_id, status)

    }

    protected inner class TestUpcomingCacheMapper : UpcomingCacheMapper {
        override fun map(upcomingEntity: UpcomingEntity) =
            Upcoming(upcomingEntity.capsule_id, upcomingEntity.status)
    }
}