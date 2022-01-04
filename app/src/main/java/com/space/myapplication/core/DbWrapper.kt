package com.space.myapplication.core

import io.realm.Realm
import io.realm.RealmObject

interface DbWrapper<T> {
    fun createObject(primary: Any): T

    abstract class Base<T : RealmObject>(private val realm: Realm) : DbWrapper<T> {
        override fun createObject(primary: Any): T = realm.createObject(dbClass(), primary)
        protected abstract fun dbClass(): Class<T>
    }
}