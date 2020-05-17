package com.example.eventsapp.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url)
        .centerCrop()
        .placeholder(CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }).into(this)
}