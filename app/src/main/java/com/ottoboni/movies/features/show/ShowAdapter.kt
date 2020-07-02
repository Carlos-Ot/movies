package com.ottoboni.movies.features.show

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.movies.R
import com.ottoboni.movies.bindings.loadImage
import com.ottoboni.movies.databinding.ItemShowBinding
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.extensions.layoutInflater
import com.ottoboni.movies.extensions.onClick
import kotlin.math.ceil

class ShowAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val itemClickListener: (View, Int, Int) -> Unit
) :
    RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    var items: List<Show> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemShowBinding.inflate(parent.layoutInflater, parent, false),
        lifecycleOwner = lifecycleOwner
    ).onClick(itemClickListener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

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