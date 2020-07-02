package com.ottoboni.movies.extensions

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

val View.layoutInflater get(): LayoutInflater = LayoutInflater.from(context)

fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}