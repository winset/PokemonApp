package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.domain.UpcomingDomain
import java.lang.Exception

interface UpcomingDataToDomainMapper:Abstract.Mapper {
    fun map(upcomings: List<UpcomingDto>):UpcomingDomain
    fun map(exception: Exception):UpcomingDomain

    class Base():UpcomingDataToDomainMapper{
        override fun map(upcomings: List<UpcomingDto>): UpcomingDomain {
            TODO("Not yet implemented")
        }

        override fun map(exception: Exception): UpcomingDomain {
            TODO("Not yet implemented")
        }
    }
}