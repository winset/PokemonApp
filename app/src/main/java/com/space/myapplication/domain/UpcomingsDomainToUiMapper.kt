package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.presentation.ResourceProvider
import com.space.myapplication.presentation.UpcomingCommunication
import com.space.myapplication.presentation.UpcomingUi

interface UpcomingsDomainToUiMapper : Abstract.Mapper {

    fun map(upcomings: List<Upcoming>): UpcomingUi
    fun map(errorType: ErrorType): UpcomingUi

    class Base(
        private val communication: UpcomingCommunication,
        private val resourceProvider: ResourceProvider
    ) : UpcomingsDomainToUiMapper {
        override fun map(upcomings: List<Upcoming>) = UpcomingUi.Success(communication, upcomings)

        override fun map(errorType: ErrorType) =
            UpcomingUi.Fail(communication, errorType, resourceProvider)
    }
}