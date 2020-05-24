package com.mmaquera.showmovies.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

fun ImageView.openWithGlide(glideUrl: GlideUrl, size: Int) {
    Glide.with(this.context)
        .load(glideUrl)
        .override(size)
        .into(this)
}