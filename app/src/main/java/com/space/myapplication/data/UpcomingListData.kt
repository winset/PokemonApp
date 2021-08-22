package com.space.myapplication.data

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.net.UpcomingDto
import com.space.myapplication.domain.UpcomingDomain

sealed class UpcomingListData : Abstract.Object<UpcomingDomain,UpcomingDataToDomainMapper>() {
    class Success(private val upcomings: List<Upcoming>):UpcomingListData(){
        override fun map(mapper: UpcomingDataToDomainMapper): UpcomingDomain {
            return mapper.map(upcomings)
        }
    }
    class Fail(private val exception:Exception): UpcomingListData() {
        override fun map(mapper: UpcomingDataToDomainMapper): UpcomingDomain {
            return mapper.map(exception)
        }
    }
}