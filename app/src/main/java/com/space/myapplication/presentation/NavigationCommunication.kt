package com.space.myapplication.presentation

import com.space.myapplication.core.Communication

interface NavigationCommunication:Communication<Int> {
    class Base: Communication.Base<Int>(),NavigationCommunication
}