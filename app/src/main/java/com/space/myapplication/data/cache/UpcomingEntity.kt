package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UpcomingEntity : RealmObject(), Abstract.Entity<Upcoming, UpcomingCacheMapper> {
    @PrimaryKey
    var capsule_id: String = ""
    var status: String = ""

    override fun map(mapper: UpcomingCacheMapper) = Upcoming(capsule_id, status)
}
