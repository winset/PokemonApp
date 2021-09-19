package com.space.myapplication.presentation

import com.space.myapplication.R
import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.domain.ErrorType

sealed class UpcomingUi:Abstract.Object<Unit,Abstract.Mapper.Empty>() {

    class Success(
        private val communication: UpcomingCommunication,
        private val upcoming: List<Upcoming>): UpcomingUi() {
        override fun map(mapper: Abstract.Mapper.Empty) = communication.show(upcoming)
    }

    class Fail(
        private val communication: UpcomingCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ): UpcomingUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val msgId = when(errorType){ // todo move to other class
                ErrorType.NO_CONNECTION-> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE->R.string.server_not_available
                else->R.string.something_go_wrong
            }
            communication.show(resourceProvider.getString(msgId))
        }
    }
}