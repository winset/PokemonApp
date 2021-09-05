package com.space.myapplication.domain

import com.space.myapplication.data.UpcomingDataToDomainMapper
import com.space.myapplication.data.UpcomingRepository
import com.space.myapplication.presentation.UpcomingUi

interface UpcomingsInteractor {
    suspend fun getUpcomings(): UpcomingDomain

    class Base(
        private val upcomingRepository: UpcomingRepository,
        private val mapper: UpcomingDataToDomainMapper
    ) : UpcomingsInteractor {
        override suspend fun getUpcomings() = upcomingRepository.getUpcoming().map(mapper)
    }
}