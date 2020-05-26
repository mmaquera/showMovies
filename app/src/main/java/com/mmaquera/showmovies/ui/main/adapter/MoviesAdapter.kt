package com.mmaquera.showmovies.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.databinding.RowMovieBinding
import com.mmaquera.showmovies.extensions.openWithGlide
import com.mmaquera.showmovies.ui.main.model.MovieModel
import com.mmaquera.showmovies.ui.main.listener.MoviesInterface

class MoviesAdapter(private val glideSettingsUrl: GlideSettingsUrl, private val adapterInterface: MoviesInterface) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    var items = emptyList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }

    fun replace(movies: List<MovieModel>) {
        this.items = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: RowMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movieModel: MovieModel) = with(itemBinding) {
            textTitle.text = movieModel.title
            textDescription.text = movieModel.getDescriptionResume()
            movieModel.image?.let {
                imageMovie.openWithGlide(glideSettingsUrl.invoke(it, "w500"))
            }

            itemBinding.root.setOnClickListener { adapterInterface.onMovieClick(movieModel.id) }

        }
    }
}