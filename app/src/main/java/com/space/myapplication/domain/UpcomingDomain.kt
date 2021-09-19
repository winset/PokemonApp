package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.presentation.UpcomingsUi
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.Exception

sealed class UpcomingDomain : Abstract.Object<UpcomingsUi, UpcomingsDomainToUiMapper> {

    class Success(private val upcomings: List<Upcoming>) : UpcomingDomain() {
        override fun map(mapper: UpcomingsDomainToUiMapper) = mapper.map(upcomings)
    }

    class Fail(private val exception: Exception) : UpcomingDomain() {
        override fun map(
            mapper: UpcomingsDomainToUiMapper
        ) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}