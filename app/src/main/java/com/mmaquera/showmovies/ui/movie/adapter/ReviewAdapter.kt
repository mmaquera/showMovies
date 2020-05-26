package com.mmaquera.showmovies.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.databinding.RowReviewBinding
import com.mmaquera.showmovies.ui.movie.model.ReviewModel


class ReviewAdapter:
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var items = emptyList<ReviewModel>()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val itemBinding: RowReviewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(reviewModel: ReviewModel) = with(itemBinding) {
            textAuthor.text = reviewModel.author
            textMessage.text = reviewModel.message
        }
    }
}