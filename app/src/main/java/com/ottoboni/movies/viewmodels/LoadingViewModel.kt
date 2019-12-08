package com.ottoboni.movies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.movies.domain.datasource.genre.GenreDataSource
import com.ottoboni.movies.domain.datasource.show.ShowDataSource
import com.ottoboni.movies.domain.model.Show
import kotlinx.coroutines.launch

class LoadingViewModel(
    private val genreRepository: GenreDataSource,
    private val showRepository: ShowDataSource
) : ViewModel() {

    val mutableLiveData: MutableLiveData<List<Show>> = MutableLiveData(emptyList())

    init {
        loadShows()
    }

    private fun loadShows() {
        viewModelScope.launch {
            val genres = genreRepository.loadGenres()

            val shows = showRepository.fetchPopular(1, "")

            shows.forEach {
                it.apply {
                    this.genres = genres.filter { genre ->
                        this.genreIds.contains(genre.id)
                    }
                }
            }
            mutableLiveData.postValue(shows)
        }
    }
}