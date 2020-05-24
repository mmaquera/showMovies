package com.mmaquera.showmovies.ui.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.databinding.RowMovieBinding
import com.mmaquera.showmovies.extensions.openWithGlide
import com.mmaquera.showmovies.ui.main.model.MovieModel

class MoviesAdapter(private val glideSettingsUrl: GlideSettingsUrl) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var items: List<MovieModel> = emptyList()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val itemBinding: RowMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movieModel: MovieModel) = with(itemBinding) {
            textTitle.text = movieModel.title
            textDescription.text = movieModel.getDescriptionResume()
            imageMovie.openWithGlide(glideSettingsUrl.invoke(movieModel.image, "w500"), 500)
        }
    }
}