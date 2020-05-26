package com.mmaquera.showmovies.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.databinding.RowFilterBinding
import com.mmaquera.showmovies.ui.main.listener.FilterInterface
import com.mmaquera.showmovies.ui.main.model.FilterModel

class FilterAdapter(private val adapterInterface: FilterInterface) :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var items = emptyList<FilterModel>()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowFilterBinding.inflate(
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

    fun updateList(position: Int) {
        val list = items.mapIndexed { index, filterModel ->
            FilterModel(filterModel.id, filterModel.name, index == position)
        }
        this.items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemBinding: RowFilterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(filterModel: FilterModel) = with(itemBinding) {
            textFilter.text = filterModel.name

            textFilter.setBackgroundDrawable(
                AppCompatResources.getDrawable(
                    itemBinding.root.context,
                    filterModel.assignSelected()
                )
            )

            textFilter.setTextColor(ContextCompat.getColor(itemBinding.root.context, filterModel.getColor()))

            itemBinding.root.setOnClickListener {
                adapterInterface.onFilterClick(filterModel.id, adapterPosition)
            }
        }

        private fun FilterModel.getColor(): Int{
            return if(status) R.color.colorPrimary else android.R.color.black
        }

        private fun FilterModel.assignSelected(): Int {
            return if (status) R.drawable.text_filter_corner_select else R.drawable.text_filter_corner
        }
    }
}