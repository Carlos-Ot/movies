package com.ottoboni.movies.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ottoboni.movies.R

val View.layoutInflater get(): LayoutInflater = LayoutInflater.from(context)

fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit):
    T {
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, itemViewType)
        }
        return this
    }

fun Activity.showErrorSnackbar(
    anchorView: View,
    @StringRes actionTextRes: Int
) = Snackbar
    .make(anchorView, resources.getString(actionTextRes), Snackbar.LENGTH_LONG)
    .apply {
        setActionTextColor(getColor(R.color.white))
        view.background = getDrawable(R.drawable.shape_error_snackbar_background)
        show()
    }

fun Activity.showErrorSnackbar(
    anchorView: View,
    actionText: String
) = Snackbar
    .make(anchorView, actionText, Snackbar.LENGTH_LONG)
    .apply {
        setActionTextColor(getColor(R.color.white))
        view.background = getDrawable(R.drawable.shape_error_snackbar_background)
        show()
    }
