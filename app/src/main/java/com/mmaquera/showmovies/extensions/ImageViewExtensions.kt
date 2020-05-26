package com.mmaquera.showmovies.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.mmaquera.showmovies.R

fun ImageView.openWithGlide(glideUrl: GlideUrl) {
    Glide.with(this.context)
        .load(glideUrl)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}