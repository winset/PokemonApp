package com.space.myapplication.presentation

import com.space.myapplication.core.Communication

interface MainCommunication:Communication<Int> {
    class Base: Communication.Base<Int>(),MainCommunication
}