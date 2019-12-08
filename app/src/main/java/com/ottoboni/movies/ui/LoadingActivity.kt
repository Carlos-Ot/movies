package com.ottoboni.movies.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ottoboni.movies.R
import com.ottoboni.movies.viewmodels.LoadingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadingActivity : AppCompatActivity() {

    private val loadingViewModel: LoadingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingViewModel.mutableLiveData.observe(this, Observer {
            Log.d("Loading", "Shows: $it")
        })
    }
}
