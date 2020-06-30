package com.ottoboni.movies.features.loading

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import kotlinx.coroutines.launch
import java.util.*

class LoadingViewModel @ViewModelInject constructor(
    private val genreRepository: IGenreRepository,
    private val showRepository: IShowRepository
) : ViewModel() {

    val mutableLiveData: MutableLiveData<List<Show>> = MutableLiveData(emptyList())

    init {
        loadShows()
    }

    private fun loadShows() {
        viewModelScope.launch {
            val genres = genreRepository.loadGenres()

            val shows = showRepository.fetchPopular(1, Locale.getDefault().toLanguageTag())

            shows?.forEach {
                it.apply {
                    this.genres = genres?.filter { genre ->
                        this.genreIds?.contains(genre.id) ?: false
                    } ?: emptyList()
                }
            }
            mutableLiveData.postValue(shows)
        }
    }
}