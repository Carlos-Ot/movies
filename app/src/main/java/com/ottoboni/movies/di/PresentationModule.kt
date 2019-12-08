package com.ottoboni.movies.di

import com.ottoboni.movies.viewmodels.LoadingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoadingViewModel(
            genreRepository = get(),
            showRepository = get()
        )
    }
}