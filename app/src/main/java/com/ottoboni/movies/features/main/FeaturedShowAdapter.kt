package com.ottoboni.movies.features.main

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.movies.bindings.loadImage
import com.ottoboni.movies.databinding.ItemFeaturedShowBinding
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.extensions.layoutInflater

class FeaturedShowAdapter(private val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<FeaturedShowAdapter.ViewHolder>() {

    var items: List<Show> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemFeaturedShowBinding.inflate(parent.layoutInflater, parent, false),
        lifecycleOwner = lifecycleOwner
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(private val binding: ItemFeaturedShowBinding, lifecycleOwner: LifecycleOwner) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = lifecycleOwner
        }

        fun bind(show: Show) = with(binding) {
            ivItemFeaturedShowBackdrop.loadImage(show.backdropPath, null, null)
            tvFeaturedShowTitle.text = show.name
        }
    }
}