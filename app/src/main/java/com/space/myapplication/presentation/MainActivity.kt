package com.space.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.myapplication.core.SpaceApp
import com.space.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as SpaceApp).mainViewModel

        val upcomingAdapter = PokemonAdapter(object : PokemonAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPokemons()
            }
        })

        binding.upcomingRv.adapter = upcomingAdapter
        viewModel.observe(this, {
            upcomingAdapter.update(it)
        })
        viewModel.getPokemons()

    }
}