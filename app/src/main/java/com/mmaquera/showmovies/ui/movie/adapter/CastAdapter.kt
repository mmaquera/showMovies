package com.mmaquera.showmovies.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.databinding.RowActorBinding
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.extensions.openWithGlideCorner
import com.mmaquera.showmovies.ui.movie.model.ActorModel

class CastAdapter(private val glideSettingsUrl: GlideSettingsUrl) :
    RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    var items = emptyList<ActorModel>()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }

    inner class ViewHolder(private val itemBinding: RowActorBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(actorModel: ActorModel) = with(itemBinding) {
            actorModel.image?.let {
                imageCast.openWithGlideCorner(glideSettingsUrl.invoke(it, "w500"))
            }
            textName.text = actorModel.name
            textCharacter.text = actorModel.character
        }
    }
}