package com.mmaquera.showmovies.di.settings

import com.bumptech.glide.load.model.GlideUrl
import com.mmaquera.showmovies.data.storage.Constants
import com.mmaquera.showmovies.data.storage.Storage
import javax.inject.Inject

class GlideSettingsUrl @Inject constructor(private val storage: Storage) {

    fun invoke(name: String, imageSize: String): GlideUrl {
        val url = "https://dev-candidates.wifiesta.com/images$name?size=$imageSize"

        return GlideUrl(url) {
            mapOf(Pair(Constants.HEADER_API_KEY, storage.getString(Constants.TOKEN)))
        }
    }
}