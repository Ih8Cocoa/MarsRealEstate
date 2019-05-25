package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<MarsProperty, PhotoGridAdapter.PropertyViewHolder>(
        DiffCallback
) {
    class PropertyViewHolder(private val binding: GridViewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(property: MarsProperty) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val listener: (property: MarsProperty) -> Unit) {
        fun onClick(property: MarsProperty) {
            listener(property)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridViewItemBinding.inflate(inflater)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }
        holder.bind(property)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty) =
                oldItem === newItem

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty) =
                oldItem.id == newItem.id

    }
}