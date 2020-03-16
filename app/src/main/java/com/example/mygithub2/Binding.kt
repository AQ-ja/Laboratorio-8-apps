package com.example.mygithub2

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun bindImage(imgView: ImageView, img: String?) {
    img?.let {
        val Uri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(Uri)
            .into(imgView)
    }
}