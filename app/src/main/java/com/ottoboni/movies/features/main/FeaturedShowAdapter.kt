package com.ottoboni.movies.features.main

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.movies.R
import com.ottoboni.movies.bindings.loadImage
import com.ottoboni.movies.databinding.ItemFeaturedShowBinding
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.extensions.layoutInflater
import com.ottoboni.movies.extensions.onClick

class FeaturedShowAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val itemClickListener: (View, Int, Int) -> Unit
) :
    RecyclerView.Adapter<FeaturedShowAdapter.ViewHolder>() {

    var items: List<Show> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemFeaturedShowBinding.inflate(parent.layoutInflater, parent, false),
        lifecycleOwner = lifecycleOwner
    ).onClick(itemClickListener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(private val binding: ItemFeaturedShowBinding, lifecycleOwner: LifecycleOwner) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = lifecycleOwner
        }

        fun bind(show: Show) = with(binding) {
            root.context?.resources?.let { resources ->
                ivItemFeaturedShowBackdrop.loadImage(
                    show.backdropUrl,
                    resources.getDrawable(R.drawable.icon_backdrop_placeholder, root.context.theme),
                    resources.getDrawable(R.drawable.icon_backdrop_error, root.context.theme)
                )
            }
            tvFeaturedShowTitle.text = show.name
        }
    }
}
