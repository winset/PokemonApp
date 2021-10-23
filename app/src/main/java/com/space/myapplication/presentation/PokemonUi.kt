package com.space.myapplication.presentation

import com.space.myapplication.core.Abstract

sealed class PokemonUi : Abstract.Object<Unit, PokemonUi.StringMapper> {
    override fun map(mapper: StringMapper)  = Unit

    object Progress : PokemonUi()
    class Base(
        private val name: String,
        private val url: String
    ) : PokemonUi() {
        override fun map(mapper: StringMapper) = mapper.map(name,url)
    }

    data class Fail(private val message: String) : PokemonUi(){
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper:Abstract.Mapper{
        fun map(text:String,url: String){}
        fun map(text: String){}
    }
}
