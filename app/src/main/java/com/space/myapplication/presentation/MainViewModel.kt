package com.space.myapplication.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.space.myapplication.core.Communication
import com.space.myapplication.core.Read
import com.space.myapplication.core.Save

class MainViewModel(
    private val navigator: Navigator,
    private val communication: Communication<Int>
) : ViewModel() {

    init {
        communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        val newScreen = currentScreen - 1
        if (newScreen >= 0) {
            communication.map(newScreen)
        }
        return exit
    }
}

interface Navigator : Save<Int>, Read<Int> {
    class Base(context: Context) : Navigator {

        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun read(): Int = sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)


        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        }

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigator"
            const val CURRENT_SCREEN_KEY = "screenId"
        }
    }
}

class Screen {
    companion object {
        const val POKEMONS_SCREEN = 0
        const val SPECIES_SCREEN = 1
    }
}