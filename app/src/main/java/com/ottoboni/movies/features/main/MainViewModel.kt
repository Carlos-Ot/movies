package com.ottoboni.movies.features.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.setGenres
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import com.ottoboni.movies.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val genreRepository: IGenreRepository,
    private val showRepository: IShowRepository
) : ViewModel() {

    private val _trending = MutableLiveData(emptyList<Show>())

    val featured = _trending.map { shows -> shows.take(5) }

    val trending = _trending.switchMap { shows ->
        featured.map { featured -> shows.subtract(featured).toList() }
    }

    private val _popular = MutableLiveData(emptyList<Show>())
    val popular: LiveData<List<Show>> get() = _popular

    private val _actionOnMoreTrendingButtonClicked = SingleLiveEvent<Any>()
    val actionOnMoreTrendingButtonClicked: LiveData<Any> get() = _actionOnMoreTrendingButtonClicked

    private val _actionOnMorePopularButtonClicked = SingleLiveEvent<Any>()
    val actionOnMorePopularButtonClicked: LiveData<Any> get() = _actionOnMorePopularButtonClicked

    init {
        loadShows()
    }

    private fun loadShows() = viewModelScope.launch {
        val genres = genreRepository.loadGenres()

        val trending = showRepository.fetchTrending()
        trending?.setGenres(genres)
        _trending.postValue(trending)

        val popular = showRepository.fetchPopular(1, Locale.getDefault().toLanguageTag())
        popular?.setGenres(genres)
        _popular.postValue(popular)
    }

    fun onMoreTrendingButtonClicked() = _actionOnMoreTrendingButtonClicked.call()

    fun onMorePopularButtonClicked() = _actionOnMorePopularButtonClicked.call()
}