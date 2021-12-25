package com.space.myapplication.utils

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.IllegalArgumentException

class RecyclerPaging(
    recycler: RecyclerView,
    private val loadMore: (Int) -> Unit,
    private val isLoading:()->Boolean
) : RecyclerView.OnScrollListener() {

    var currentPage = 0
    var threshold = 15

    init {
        recycler.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy>0){
            recyclerView.layoutManager?.let {
                val totalItemsCount = it.itemCount
                val pastVisibleItemPosition = when (it) {
                    is LinearLayoutManager -> it.findLastVisibleItemPosition()
                    is GridLayoutManager -> it.findLastVisibleItemPosition()
                    is StaggeredGridLayoutManager -> findLastVisibleItemPosition(
                        it.findLastVisibleItemPositions(null)
                    )
                    else -> throw IllegalArgumentException("RecyclerPaging don't support this layout.")
                }

                if ((threshold + pastVisibleItemPosition) >= totalItemsCount) {
                    loadMore(++currentPage)
                }
            }
        }
    }

    private fun findLastVisibleItemPosition(lastVisibleItems: IntArray): Int {
        return lastVisibleItems.maxOfOrNull { it } ?: lastVisibleItems[0]
    }
}