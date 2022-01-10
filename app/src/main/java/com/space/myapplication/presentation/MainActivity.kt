package com.space.myapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.space.myapplication.R
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.ActivityMainBinding

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
            val fragmentId = when (it) {
                Screen.POKEMONS_SCREEN -> R.id.pokemonsFragment
                Screen.SPECIES_SCREEN -> R.id.speciesFragment
                else -> throw IllegalArgumentException("Wrong screen id $it")
            }
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            val graph = navController.graph
            graph.startDestination = fragmentId
            navController.setGraph(graph, intent.extras)

        })
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack()) super.onBackPressed()
    }
}