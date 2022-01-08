package com.space.myapplication.core

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }
    }
}