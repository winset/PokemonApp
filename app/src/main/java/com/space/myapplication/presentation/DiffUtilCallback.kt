package com.space.myapplication.presentation

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val newList: List<PokemonUi>,
    private val oldList: List<PokemonUi>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)=
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])
}