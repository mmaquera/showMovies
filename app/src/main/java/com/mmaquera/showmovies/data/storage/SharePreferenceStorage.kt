package com.mmaquera.showmovies.data.storage

import android.content.Context
import javax.inject.Inject

class SharePreferenceStorage @Inject constructor (context: Context) : Storage{

    private val sharedPreferences = context.getSharedPreferences(Constants.FILE, Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()){
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return requireNotNull(sharedPreferences.getString(key, Constants.EMPTY))
    }
}