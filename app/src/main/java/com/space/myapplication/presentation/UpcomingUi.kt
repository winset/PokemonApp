package com.space.myapplication.presentation

import com.space.myapplication.core.Abstract

sealed class UpcomingUi : Abstract.Object<Unit, UpcomingUi.StringMapper> {
    override fun map(mapper: StringMapper)  = Unit

    object Progress : UpcomingUi()
    class Base(
        private val capsule_id: String,
        private val status: String
    ) : UpcomingUi() {
        override fun map(mapper: StringMapper) = mapper.map(capsule_id)
    }

    class Fail(private val message: String) : UpcomingUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper:Abstract.Mapper{
        fun map(text:String)
    }
}
