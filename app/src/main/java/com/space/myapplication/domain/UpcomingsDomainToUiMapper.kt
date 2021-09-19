package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.presentation.ResourceProvider
import com.space.myapplication.presentation.UpcomingsUi

interface UpcomingsDomainToUiMapper : Abstract.Mapper {

    fun map(upcomings: List<Upcoming>): UpcomingsUi
    fun map(errorType: ErrorType): UpcomingsUi

    class Base(
        private val resourceProvider: ResourceProvider
    ) : UpcomingsDomainToUiMapper {
        override fun map(upcomings: List<Upcoming>) = UpcomingsUi.Success( upcomings)
        override fun map(errorType: ErrorType) =
            UpcomingsUi.Fail( errorType, resourceProvider)
    }
}