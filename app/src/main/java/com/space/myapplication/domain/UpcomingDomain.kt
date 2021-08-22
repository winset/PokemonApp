package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.UpcomingUi
import java.lang.Exception

sealed class UpcomingDomain : Abstract.Object<UpcomingUi, Abstract.Mapper.Empty>() {
    class Success() : UpcomingDomain() {
        override fun map(mapper: Abstract.Mapper.Empty): UpcomingUi {
            TODO("Not yet implemented")
        }
    }

    class Fail(errorType:Int) : UpcomingDomain() {
        override fun map(mapper: Abstract.Mapper.Empty): UpcomingUi {
            
            TODO("Not yet implemented")
        }
    }
}