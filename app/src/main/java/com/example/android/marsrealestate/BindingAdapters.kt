package com.example.android.marsrealestate

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.MarsApiStatus
import com.example.android.marsrealestate.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val uri = it.toUri().buildUpon().scheme("https").build()
        // Load the image from uri to imgView
        Glide.with(imgView.context)
                .load(uri)
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusView: ImageView, status: MarsApiStatus?) {
    statusView.apply {
        when (status) {
            MarsApiStatus.LOADING -> {
                visibility = View.VISIBLE
                setImageResource(R.drawable.loading_animation)
            }
            MarsApiStatus.ERROR -> {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_connection_error)
            }
            MarsApiStatus.DONE -> {
                visibility = View.GONE
            }
        }
    }
}