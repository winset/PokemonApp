package com.space.myapplication.presentation.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.space.myapplication.R
import com.space.myapplication.core.PokemonApp
import com.space.myapplication.databinding.FragmentSpeciesBinding

class SpeciesFragment : Fragment() {
    private var _binding: FragmentSpeciesBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<SpeciesFragmentArgs>()
    private lateinit var viewModel: SpeciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeciesBinding.inflate(inflater)
        binding.image.transitionName = args.name
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel = (requireActivity().application as PokemonApp).speciesViewModel
        viewModel.init()
        viewModel.getInfo(args.name)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(args.url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.pokemon_placeholder)
            .centerCrop().into(binding.image)
    }
}