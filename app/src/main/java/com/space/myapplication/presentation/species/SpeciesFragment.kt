package com.space.myapplication.presentation.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.FragmentSpeciesBinding

class SpeciesFragment : Fragment() {
    private var _binding: FragmentSpeciesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SpeciesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeciesBinding.inflate(inflater)
        viewModel = (requireActivity().application as PokemonApp).speciesViewModel
        viewModel.init()
        return binding.root
    }
}