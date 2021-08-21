package com.space.myapplication.domain

import com.space.myapplication.core.Abstract
import com.space.myapplication.presentation.UpcomingUi

sealed class UpcomingDomain: Abstract.Object<UpcomingUi, Abstract.Mapper.Empty>() {
}