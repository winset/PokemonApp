package com.space.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming

interface UpcomingCommunication : Abstract.Mapper {
    fun show(upcoming: List<Upcoming>)
    fun show(errorMsg: String)

    fun observeSuccess(owner:LifecycleOwner,observer:Observer<List<Upcoming>>)
    fun observeFail(owner:LifecycleOwner,observer:Observer<String>)

    class Base():UpcomingCommunication{
        private val successLiveData = MutableLiveData<List<Upcoming>>()
        private val failLiveData = MutableLiveData<String>()

        override fun show(upcoming: List<Upcoming>) {
            successLiveData.value = upcoming
        }

        override fun show(errorMsg: String) {
            failLiveData.value = errorMsg
        }

        override fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Upcoming>>) {
            successLiveData.observe(owner,observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner,observer)
        }
    }
}