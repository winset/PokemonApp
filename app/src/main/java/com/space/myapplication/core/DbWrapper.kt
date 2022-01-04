package com.space.myapplication.core

import io.realm.Realm
import io.realm.RealmObject

interface DbWrapper<T> {
    fun createObject(primary: Any): T
    fun createObjectAutoIncremented(primaryFieldName: String): T

    abstract class Base<T : RealmObject>(private val realm: Realm) : DbWrapper<T> {
        override fun createObject(primary: Any): T = realm.createObject(dbClass(), primary)
        override fun createObjectAutoIncremented(primaryFieldName: String): T {
            val maxId: Number? = realm.where(dbClass()).max(primaryFieldName)
            val nextId = if (maxId == null) 1 else maxId.toInt() + 1
            return realm.createObject(dbClass(), nextId)
        }

        protected abstract fun dbClass(): Class<T>
    }
}