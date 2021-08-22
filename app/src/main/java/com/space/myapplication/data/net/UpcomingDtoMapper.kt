package com.space.myapplication.data.net

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface UpcomingDtoMapper : Abstract.Mapper {
    fun map(capsule_id: String, status: String): Upcoming

    class Base(): UpcomingDtoMapper {
        override fun map(capsule_id: String, status: String): Upcoming {
            return Upcoming(capsule_id, status)
        }
    }
}
