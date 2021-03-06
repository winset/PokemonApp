package com.space.myapplication.presentation.pokemons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.myapplication.R
import com.space.myapplication.core.DiffUtilCallback
import com.space.myapplication.core.LoadImage

class PokemonAdapter(
    private val onRetryClick: () -> Unit,
    private val onPokemonClick: (String, String, FragmentNavigator.Extras) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.UpcomingViewHolder>() {
    private val pokemonList = mutableListOf<PokemonUi>()

    fun update(new: List<PokemonUi>) {
        val diffUtilCallback = DiffUtilCallback(new, pokemonList)
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        pokemonList.clear()
        pokemonList.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (pokemonList[position]) {
        is PokemonUi.Base -> 0
        is PokemonUi.Fail -> 1
        else -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> UpcomingViewHolder.Base(R.layout.pokemon_item.makeView(parent), onPokemonClick)
        1 -> UpcomingViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), onRetryClick)
        else -> UpcomingViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount() = pokemonList.size

    abstract class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(pokemon: PokemonUi) {}

        class FullscreenProgress(view: View) : UpcomingViewHolder(view)

        class Base(
            view: View,
            private val onPokemonClick: (String, String, FragmentNavigator.Extras) -> Unit
        ) : UpcomingViewHolder(view) {
            private val layout = itemView.findViewById<CardView>(R.id.card_pokemon)
            private val name = itemView.findViewById<TextView>(R.id.textView)
            private val image = itemView.findViewById<ImageView>(R.id.image)
            override fun bind(pokemon: PokemonUi) {

                pokemon.map(object : PokemonUi.StringMapper {
                    override fun map(name: String, url: String) {
                        this@Base.name.text = name
                        image.transitionName = name
                        LoadImage.Base(url, R.drawable.pokemon_placeholder).load(image)
                    }
                })
                layout.setOnClickListener {
                    pokemon.map(object : PokemonUi.StringMapper {
                        override fun map(name: String, url: String) {
                            val extras = FragmentNavigatorExtras(image to name)
                            onPokemonClick(name, url, extras)
                        }
                    })
                }
            }
        }

        class Fail(view: View, private val onRetryClick: () -> Unit) : UpcomingViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.fail_message)
            private val tryAgainBtn = itemView.findViewById<Button>(R.id.try_again_btn)
            override fun bind(pokemon: PokemonUi) {
                pokemon.map(object : PokemonUi.StringMapper {
                    override fun map(errorMessage: String) {
                        message.text = errorMessage
                    }
                })
                tryAgainBtn.setOnClickListener {
                    onRetryClick()
                }
            }
        }
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)