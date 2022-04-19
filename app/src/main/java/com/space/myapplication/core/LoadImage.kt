package com.space.myapplication.core

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

interface LoadImage {

    fun load(view: ImageView)

    class Base(
        private val url: String,
        @DrawableRes private val placeholderId: Int
    ) : LoadImage {
        override fun load(view: ImageView) {
            Glide.with(view.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(placeholderId)
                .centerCrop().into(view)
        }
    }
}