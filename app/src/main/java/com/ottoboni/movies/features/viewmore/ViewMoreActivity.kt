package com.ottoboni.movies.features.viewmore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.ottoboni.movies.R
import com.ottoboni.movies.databinding.ActivityViewMoreBinding
import com.ottoboni.movies.extensions.showErrorSnackbar
import com.ottoboni.movies.features.show.ShowPagedAdapter
import com.ottoboni.movies.features.showdetails.ShowDetailsActivity
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source.POPULAR
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source.TRENDING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class ViewMoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewMoreBinding

    private val viewModel: ViewMoreViewModel by viewModels()

    private lateinit var showAdapter: ShowPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityViewMoreBinding>(this, R.layout.activity_view_more)
            .apply {
                lifecycleOwner = this@ViewMoreActivity
                viewModel = this@ViewMoreActivity.viewModel
            }

        setupToolbar()
        setupRecyclerView()
        observeEvents()
    }

    private fun setupToolbar() = with(binding.tViewMore) {
        this@ViewMoreActivity.setSupportActionBar(this)
        supportActionBar?.apply {
            title =
                if (intent.getSerializableExtra(SOURCE_EXTRA_KEY) == TRENDING)
                    resources.getString(R.string.view_more_trending_title_text)
                else resources.getString(R.string.view_more_popular_title_text)
        }
    }

    private fun setupRecyclerView() = with(binding.rvViewMore) {
        showAdapter = ShowPagedAdapter(this@ViewMoreActivity, showClickListener)
        adapter = showAdapter
    }

    private val showClickListener: (View, Int, Int) -> Unit = { _, position, _ ->
        viewModel.onShowClicked(position)
    }

    private fun observeEvents() = with(viewModel) {
        shows.observe(this@ViewMoreActivity) {
            showAdapter.submitList(it)
        }

        actionOnShowClicked.observe(this@ViewMoreActivity) { show ->
            show?.let {
                startActivity(ShowDetailsActivity.newIntent(this@ViewMoreActivity, it))
            }
        }

        actionOnError.observe(this@ViewMoreActivity) {
            showErrorSnackbar(binding.colViewMoreRoot, R.string.generic_error_snackbar_text)
        }

        actionOnConnectivityError.observe(this@ViewMoreActivity) {
            showErrorSnackbar(binding.colViewMoreRoot, R.string.no_internet_error_snackbar_text)
        }

        actionOnNetworkError.observe(this@ViewMoreActivity) {
            it?.let { showErrorSnackbar(binding.colViewMoreRoot, it) }
        }
    }

    class ViewMoreTrendingActivity : ViewMoreActivity()

    class ViewMorePopularActivity : ViewMoreActivity()

    companion object {
        const val SOURCE_EXTRA_KEY = "SOURCE_EXTRA_KEY"

        fun newTrendingIntent(context: Context) =
            Intent(context, ViewMoreTrendingActivity::class.java)
                .apply { putExtra(SOURCE_EXTRA_KEY, TRENDING) }

        fun newPopularIntent(context: Context) =
            Intent(context, ViewMorePopularActivity::class.java)
                .apply { putExtra(SOURCE_EXTRA_KEY, POPULAR) }
    }

    enum class Source {
        TRENDING,
        POPULAR
    }
}