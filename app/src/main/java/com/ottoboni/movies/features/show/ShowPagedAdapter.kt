package com.ottoboni.movies.features.show

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.movies.R
import com.ottoboni.movies.bindings.loadImage
import com.ottoboni.movies.databinding.ItemShowBinding
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.extensions.layoutInflater
import com.ottoboni.movies.extensions.onClick
import kotlin.math.ceil

class ShowPagedAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val itemClickListener: (View, Int, Int) -> Unit
) : PagedListAdapter<Show, ShowPagedAdapter.ViewHolder>(ShowDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemShowBinding.inflate(parent.layoutInflater, parent, false),
        lifecycleOwner = lifecycleOwner
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    class ViewHolder(
        private val binding: ItemShowBinding,
        lifecycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = lifecycleOwner
        }

        fun bind(show: Show) = with(binding) {
            root.context?.resources?.let { resources ->
                ivItemShowPoster.loadImage(
                    show.posterUrl,
                    resources.getDrawable(R.drawable.icon_poster_placeholder, root.context.theme),
                    resources.getDrawable(R.drawable.icon_poster_error, root.context.theme)
                )
            }

            show.voteAverage?.times(10)?.let {
                val voteAverage = ceil(it)
                tvItemShowPopularity.text = voteAverage.toInt().toString()
                pbItemShowPopularity.percent = voteAverage
            } ?: run { cvItemShowPopularity.visibility = View.GONE }
            tvItemShowTitle.text = show.name
        }
    }
}

private object ShowDiffCallback : DiffUtil.ItemCallback<Show>() {
    override fun areItemsTheSame(oldItem: Show, newItem: Show) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Show, newItem: Show) = oldItem == newItem
}