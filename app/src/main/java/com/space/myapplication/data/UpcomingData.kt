package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.data.net.UpcomingDtoToDataMapper
import com.space.myapplication.domain.UpcomingDomain

sealed class UpcomingData : Abstract.Object<UpcomingDomain,UpcomingDataToDomainMapper>() {
    class Success(private val upcomings: List<UpcomingDto>):UpcomingData(){
        override fun map(mapper: UpcomingDataToDomainMapper): UpcomingDomain {
            return mapper.map(upcomings)
        }
    }
    class Fail(private val exception:Exception): UpcomingData() {
        override fun map(mapper: UpcomingDataToDomainMapper): UpcomingDomain {
            return mapper.map(exception)
        }
    }
}