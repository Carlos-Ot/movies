package com.ottoboni.movies.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.PagerSnapHelper
import com.ottoboni.movies.R
import com.ottoboni.movies.databinding.ActivityMainBinding
import com.ottoboni.movies.features.show.ShowAdapter
import com.ottoboni.movies.features.viewmore.ViewMoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var featuredAdapter: FeaturedShowAdapter
    private lateinit var trendingAdapter: ShowAdapter
    private lateinit var popularAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = this@MainActivity.viewModel
            }

        setupToolbar()
        setupViews()
        observeEvents()
    }

    private fun setupToolbar() = setSupportActionBar(binding.tMain)

    private fun setupViews() = with(binding) {
        featuredAdapter = FeaturedShowAdapter(this@MainActivity)
        rvMainFeaturedShows.adapter = featuredAdapter
        PagerSnapHelper().attachToRecyclerView(rvMainFeaturedShows)
        spiMainFeaturedShowsIndicator.attachToRecyclerView(rvMainFeaturedShows)

        trendingAdapter = ShowAdapter(this@MainActivity)
        rvMainTrending.adapter = trendingAdapter

        popularAdapter = ShowAdapter(this@MainActivity)
        rvMainPopular.adapter = popularAdapter
    }

    private fun observeEvents() = with(viewModel) {
        featured.observe(this@MainActivity) {
            featuredAdapter.items = it
        }

        trending.observe(this@MainActivity) {
            trendingAdapter.items = it
        }

        popular.observe(this@MainActivity) {
            popularAdapter.items = it
        }

        actionOnMoreTrendingButtonClicked.observe(this@MainActivity) {
            startActivity(ViewMoreActivity.newTrendingIntent(this@MainActivity))
        }

        actionOnMorePopularButtonClicked.observe(this@MainActivity) {
            startActivity(ViewMoreActivity.newPopularIntent(this@MainActivity))
        }
    }
}