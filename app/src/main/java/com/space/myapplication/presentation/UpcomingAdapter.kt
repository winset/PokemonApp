package com.space.myapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.myapplication.R

class UpcomingAdapter(private val retry:Retry) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    private val upcomingList = mutableListOf<UpcomingUi>()

    fun update(new: List<UpcomingUi>) {
        upcomingList.clear()
        upcomingList.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (upcomingList[position]) {
        is UpcomingUi.Base -> 0
        is UpcomingUi.Fail -> 1
        is UpcomingUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> UpcomingViewHolder.Base(R.layout.upcoming_item.makeView(parent))
        1 -> UpcomingViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent),retry)
        else -> UpcomingViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(upcomingList[position])
    }

    override fun getItemCount() = upcomingList.size

    abstract class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(upcoming: UpcomingUi) {
        }

        class FullscreenProgress(view: View) : UpcomingViewHolder(view)
        class Base(view: View) : UpcomingViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(upcoming: UpcomingUi) {
                upcoming.map(object : UpcomingUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Fail(view: View,private val retry:Retry) : UpcomingViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.fail_message)
            private val tryAgainBtn = itemView.findViewById<Button>(R.id.try_again_btn)
            override fun bind(upcoming: UpcomingUi) {
                upcoming.map(object : UpcomingUi.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })
                tryAgainBtn.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }
    }
    interface Retry{
        fun tryAgain()
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)