package com.space.myapplication.core

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface DataObject

    interface Mapper {
        class Empty : Mapper

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R
        }
    }
}