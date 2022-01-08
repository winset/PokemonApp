package com.space.myapplication.presentation.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.core.RecyclerPaging
import com.space.myapplication.databinding.FragmentMainBinding

class PokemonsFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        viewModel = (requireActivity().application as PokemonApp).pokemonsViewModel
        viewModel.init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retry = object : PokemonAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPokemons()
            }
        }
        val upcomingAdapter = PokemonAdapter(retry, ::onPokemonClick)

        RecyclerPaging(binding.upcomingRv, ::loadMore)
        binding.upcomingRv.adapter = upcomingAdapter
        binding.upcomingRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.observe(this, {
            upcomingAdapter.update(it)
        })
        viewModel.getPokemons()
    }

    private fun loadMore() {
        viewModel.getPokemons()
    }

    private fun onPokemonClick(name: String) {
        viewModel.onPokemonClick(name)
    }
}