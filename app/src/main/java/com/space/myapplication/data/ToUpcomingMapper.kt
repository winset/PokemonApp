package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface ToUpcomingMapper : Abstract.Mapper {
    fun map(capsule_id: String, status: String): Upcoming

    class Base() : ToUpcomingMapper {
        override fun map(capsule_id: String, status: String) = Upcoming(capsule_id, status)
    }
}
