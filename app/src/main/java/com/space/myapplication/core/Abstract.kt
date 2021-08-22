package com.space.myapplication.core

abstract class Abstract {
    abstract class Object<T, M : Mapper> {
        abstract fun map(mapper: M): T
    }

    interface Entity<T, M : Mapper>{
        fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper
    }
}