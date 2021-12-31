package com.space.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.ActivityMainBinding
import com.space.myapplication.utils.RecyclerPaging

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as PokemonApp).mainViewModel

        val retry = object : PokemonAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPokemons()
            }
        }
        val upcomingAdapter = PokemonAdapter(retry)

        RecyclerPaging(binding.upcomingRv, ::loadMore)
        binding.upcomingRv.adapter = upcomingAdapter
        binding.upcomingRv.layoutManager = GridLayoutManager(this, 2)
        viewModel.observe(this, {
            upcomingAdapter.update(it)
        })
        viewModel.getPokemons()
    }

    private fun loadMore() {
        viewModel.getPokemons()
    }
}