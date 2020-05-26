package com.mmaquera.showmovies.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.mmaquera.showmovies.R

fun ImageView.openWithGlide(glideUrl: GlideUrl) {
    Glide.with(this.context)
        .load(glideUrl)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}

fun ImageView.openWithGlideCorner(glideUrl: GlideUrl) {
    Glide.with(this.context)
        .load(glideUrl)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}