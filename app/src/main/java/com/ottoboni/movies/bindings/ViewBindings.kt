package com.ottoboni.movies.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter(value = ["imageUrl", "placeholder", "error"], requireAll = false)
fun ImageView.loadImage(url: String?, placeholder: Drawable?, error: Drawable?) = load(url) {
    crossfade(true)
    placeholder(placeholder)
    error(error)
}

@BindingAdapter(value = ["imageUrl", "placeholder", "error"], requireAll = false)
fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int, @DrawableRes error: Int) =
    load(url) {
        crossfade(true)
        placeholder(placeholder)
        error(error)
    }