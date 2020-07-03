package com.ottoboni.movies.features.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.PagerSnapHelper
import com.ottoboni.movies.R
import com.ottoboni.movies.databinding.ActivityMainBinding
import com.ottoboni.movies.extensions.showErrorSnackbar
import com.ottoboni.movies.features.show.ShowAdapter
import com.ottoboni.movies.features.showdetails.ShowDetailsActivity
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
        featuredAdapter = FeaturedShowAdapter(this@MainActivity, featuredClickListener)
        rvMainFeaturedShows.adapter = featuredAdapter
        PagerSnapHelper().attachToRecyclerView(rvMainFeaturedShows)
        spiMainFeaturedShowsIndicator.attachToRecyclerView(rvMainFeaturedShows)

        trendingAdapter = ShowAdapter(this@MainActivity, trendingClickListener)
        rvMainTrending.adapter = trendingAdapter

        popularAdapter = ShowAdapter(this@MainActivity, popularClickListener)
        rvMainPopular.adapter = popularAdapter
    }

    private val featuredClickListener: (View, Int, Int) -> Unit = { _, position, _ ->
        viewModel.onFeaturedShowClicked(position)
    }

    private val trendingClickListener: (View, Int, Int) -> Unit = { _, position, _ ->
        viewModel.onTrendingShowClicked(position)
    }

    private val popularClickListener: (View, Int, Int) -> Unit = { _, position, _ ->
        viewModel.onPopularShowClicked(position)
    }

    private fun observeEvents() = with(viewModel) {
        featured.observe(this@MainActivity) { featuredAdapter.items = it }

        trending.observe(this@MainActivity) { trendingAdapter.items = it }

        popular.observe(this@MainActivity) { popularAdapter.items = it }

        actionOnMoreTrendingButtonClicked.observe(this@MainActivity) {
            startActivity(ViewMoreActivity.newTrendingIntent(this@MainActivity))
        }

        actionOnMorePopularButtonClicked.observe(this@MainActivity) {
            startActivity(ViewMoreActivity.newPopularIntent(this@MainActivity))
        }

        actionOnShowClicked.observe(this@MainActivity) { show ->
            show?.let {
                startActivity(ShowDetailsActivity.newIntent(this@MainActivity, it))
            }
        }

        actionOnError.observe(this@MainActivity) {
            showErrorSnackbar(binding.colMainRoot, R.string.generic_error_snackbar_text)
        }

        actionOnConnectivityError.observe(this@MainActivity) {
            showErrorSnackbar(binding.colMainRoot, R.string.no_internet_error_snackbar_text)
        }

        actionOnNetworkError.observe(this@MainActivity) {
            it?.let { showErrorSnackbar(binding.colMainRoot, it) }
        }
    }
}