package com.diatomicsoft.fruits.adapter.bindingadapterutil

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("image")
fun setImage(view: ImageView, url: String){
    val baseUrl = "https://iquote.diatomicsoft.com"
    Glide.with(view)
        .load(baseUrl+url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

