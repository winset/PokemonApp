package com.space.myapplication.data.net

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.UpcomingData

interface UpcomingDtoToDataMapper : Abstract.Mapper {
    fun map(capsule_id: String, status: String): UpcomingData
}
