package com.space.myapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.myapplication.R
import com.space.myapplication.core.Upcoming

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private val upcomingList = mutableListOf<Upcoming>()

    fun update(new:List<Upcoming>){
        upcomingList.clear()
        upcomingList.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_item, parent,false)
        return UpcomingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(upcomingList[position])
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    inner class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(upcoming: Upcoming) {
            itemView.findViewById<TextView>(R.id.textView).text = upcoming.capsule_id
        }
    }
}