package com.space.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.ActivityMainBinding
import com.space.myapplication.utils.RecyclerPaging

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as PokemonApp).mainViewModel

        val retry = object : PokemonAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPokemons()
            }
        }
        val upcomingAdapter = PokemonAdapter(retry)

        RecyclerPaging(binding.upcomingRv, ::loadMore, { viewModel.isLoading })
        binding.upcomingRv.adapter = upcomingAdapter

        viewModel.observe(this, {
            upcomingAdapter.update(it)
        })
        viewModel.getPokemons()
    }

    private fun loadMore(count: Int) {
        //Log.d("TAG", "loadMore: $count")
    }
}