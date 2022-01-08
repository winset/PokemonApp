package com.space.myapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.ActivityMainBinding
import com.space.myapplication.presentation.pokemons.PokemonsFragment
import com.space.myapplication.presentation.species.SpeciesFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as PokemonApp).mainViewModel
        viewModel.observe(this, {
            val fragment = when (it) {
                Screen.POKEMONS_SCREEN -> PokemonsFragment()
                Screen.SPECIES_SCREEN -> SpeciesFragment()
                else -> throw IllegalArgumentException("Wrong screen id $it")
            }
            supportFragmentManager.beginTransaction().replace(binding.container.id, fragment)
                .commit()
        })
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack()) super.onBackPressed()
    }
}