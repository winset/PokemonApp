package com.space.myapplication.presentation.pokemons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.space.myapplication.core.RecyclerPaging
import com.space.myapplication.databinding.FragmentMainBinding
import com.space.myapplication.di.app.AppDepsProvider
import com.space.myapplication.di.app.ViewModelFactory
import com.space.myapplication.di.pokemon.DaggerPokemonComponent
import javax.inject.Inject


class PokemonsFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PokemonsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
         DaggerPokemonComponent.builder().deps(AppDepsProvider.deps)
            .build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        val pokemonAdapter = PokemonAdapter(::retryClick, ::onPokemonClick)
        RecyclerPaging(binding.pokemonRv, ::loadMore)

        binding.pokemonRv.adapter = pokemonAdapter
        binding.pokemonRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.observe(viewLifecycleOwner) {
            pokemonAdapter.update(it)
            (view.parent as? ViewGroup)?.doOnPreDraw { startPostponedEnterTransition() }
        }

        viewModel.getPokemons()
    }

    private fun loadMore() {
        viewModel.getPokemons()
    }

    private fun retryClick(){
        viewModel.getPokemons()
    }

    private fun onPokemonClick(name: String, url: String, extras: FragmentNavigator.Extras) {
        val directions =
            PokemonsFragmentDirections.actionPokemonsFragmentToSpeciesFragment(name, url)
        findNavController().navigate(directions, extras)
    }
}