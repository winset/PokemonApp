package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.presentation.UpcomingUi

interface UpcomingsToDomainMapper : Abstract.Mapper {

    fun map(upcomings: List<Upcoming>): UpcomingUi
    fun map(errorType: ErrorType): UpcomingUi

    class Base : UpcomingsToDomainMapper {
        override fun map(upcomings: List<Upcoming>): UpcomingUi {
            TODO("Not yet implemented")
        }

        override fun map(errorType: ErrorType): UpcomingUi {
            TODO("Not yet implemented")
        }
    }
}