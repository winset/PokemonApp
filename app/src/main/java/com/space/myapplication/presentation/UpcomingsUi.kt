package com.space.myapplication.presentation

import com.space.myapplication.R
import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.domain.ErrorType

sealed class UpcomingsUi : Abstract.Object<Unit, UpcomingCommunication> {

    class Success(
        private val upcoming: List<Upcoming>
    ) : UpcomingsUi() {
        override fun map(mapper: UpcomingCommunication) {
            val upcomingsUi  = upcoming.map {
                UpcomingUi.Base(it.capsule_id,it.status)
            }
            mapper.map(upcomingsUi)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : UpcomingsUi() {
        override fun map(mapper: UpcomingCommunication) {
            val msgId = when (errorType) { // todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
                else -> R.string.something_go_wrong
            }
            val message = resourceProvider.getString(msgId)
            mapper.map(listOf(UpcomingUi.Fail(message)))
        }
    }
}