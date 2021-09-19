package com.space.myapplication.data

import com.space.myapplication.core.Upcoming


abstract class BaseUpcomingRepositoryTest {

    protected inner class TestToUpcomingMapper : ToUpcomingMapper {
        override fun map(capsule_id: String, status: String) = Upcoming(capsule_id, status)

    }

    protected inner class TestUpcomingCacheMapper : ToUpcomingMapper {
        override fun map(capsule_id: String, status: String): Upcoming =
            Upcoming(capsule_id, status)
    }
}