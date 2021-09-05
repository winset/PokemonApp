package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.domain.UpcomingDomain
import java.lang.Exception

interface UpcomingDataToDomainMapper : Abstract.Mapper {
    fun map(upcomings: List<Upcoming>): UpcomingDomain
    fun map(exception: Exception): UpcomingDomain

    class Base() : UpcomingDataToDomainMapper {
        override fun map(upcomings: List<Upcoming>) = UpcomingDomain.Success(upcomings)
        override fun map(exception: Exception) = UpcomingDomain.Fail(exception)
    }
}