package com.space.myapplication.data.cache

import com.space.myapplication.core.Upcoming

interface UpcomingCacheDataSource {
    fun getUpcomingList(): List<UpcomingEntity>
    fun saveUpcomingList(upcomingList: List<Upcoming>)

    class Base(private val realmProvider: RealmProvider) : UpcomingCacheDataSource {
        override fun getUpcomingList(): List<UpcomingEntity> {
            realmProvider.provide().use { realm ->
                val upcomingEntities =
                    realm.where(UpcomingEntity::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(upcomingEntities)
            }
        }

        override fun saveUpcomingList(upcomingList: List<Upcoming>) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    upcomingList.forEach { upcoming ->

                        val maxId: Number? = it.where(UpcomingEntity::class.java).max("id")
                        val nextId = if (maxId == null) 1 else maxId.toInt() + 1
                        val upcomingEntity = it.createObject(UpcomingEntity::class.java,nextId)
                        upcomingEntity.capsule_id = upcoming.capsule_id
                        upcomingEntity.status = upcoming.status
                    }
                }
            }
    }
}

/*
interface DataSource<T> {
    fun getData(): T
}

interface MutableDataSource<R> {
    fun save(data: R)
}

interface CacheDataSource<T, R> : DataSource<T>, MutableDataSource<R>
interface CloudDataSource<E> : DataSource<E>
interface UpcomingCacheDataSourceNew : CacheDataSource<List<UpcomingEntity>, List<Upcoming>>
*/
