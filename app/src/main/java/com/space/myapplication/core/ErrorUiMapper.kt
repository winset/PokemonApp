package com.space.myapplication.core

import com.space.myapplication.R

interface ErrorUiMapper {
    fun map(errorType: ErrorType): String

    class Base(
        private val resourceProvider: ResourceProvider
    ) : ErrorUiMapper {
        override fun map(errorType: ErrorType): String {
            val msgId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection
                ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
                else -> R.string.something_go_wrong
            }
            return resourceProvider.getString(msgId)
        }
    }
}