package com.space.myapplication.data.cache

import com.space.myapplication.core.Abstract
import com.space.myapplication.core.Upcoming
import com.space.myapplication.data.ToUpcomingMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UpcomingEntity : RealmObject(), Abstract.Object<Upcoming, ToUpcomingMapper> {
    @PrimaryKey
    var id:Int = -1
    var capsule_id: String = ""
    var status: String = ""

    override fun map(mapper: ToUpcomingMapper) = Upcoming(capsule_id, status)
}
