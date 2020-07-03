package com.ottoboni.movies.features.showdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ottoboni.movies.R
import com.ottoboni.movies.databinding.ActivityShowDetailsBinding
import com.ottoboni.movies.domain.model.Show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailsBinding

    private val viewModel: ShowDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<ActivityShowDetailsBinding>(this, R.layout.activity_show_details)
            .apply {
                lifecycleOwner = this@ShowDetailsActivity
                viewModel = this@ShowDetailsActivity.viewModel
            }
    }

    private fun setupToolbar() = setSupportActionBar(binding.tShowDetails)

    companion object {
        const val SHOW_EXTRA_KEY = "SHOW_EXTRA_KEY"

        fun newIntent(context: Context, show: Show) =
            Intent(context, ShowDetailsActivity::class.java)
                .apply { putExtra(SHOW_EXTRA_KEY, show) }
    }
}
