package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface UpcomingCommunication : Abstract.Mapper {
    fun map(upcoming: List<UpcomingUi>)

    fun observe(owner:LifecycleOwner, observer:Observer<List<UpcomingUi>>)

    class Base():UpcomingCommunication{
        private val listLiveData = MutableLiveData<List<UpcomingUi>>()

        override fun map(upcoming: List<UpcomingUi>) {
            listLiveData.value = upcoming
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UpcomingUi>>) {
            listLiveData.observe(owner,observer)
        }
    }
}