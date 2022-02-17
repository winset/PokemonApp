package com.space.myapplication.presentation.species

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.space.myapplication.R
import com.space.myapplication.databinding.FragmentSpeciesBinding
import com.space.myapplication.di.app.AppDepsProvider
import com.space.myapplication.di.app.ViewModelFactory
import com.space.myapplication.di.species.DaggerSpeciesComponent
import javax.inject.Inject

class SpeciesFragment : Fragment() {
    private var _binding: FragmentSpeciesBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<SpeciesFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[SpeciesViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        DaggerSpeciesComponent.builder().deps(AppDepsProvider.deps)
            .build().inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeciesBinding.inflate(inflater)
        binding.image.transitionName = args.name
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

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