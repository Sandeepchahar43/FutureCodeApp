package com.example.futurecodeapp.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {

    Glide.with(view.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .into(view)
}
