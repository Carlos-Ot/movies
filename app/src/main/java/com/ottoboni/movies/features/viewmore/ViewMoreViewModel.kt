package com.ottoboni.movies.features.viewmore

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.setGenres
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Companion.SOURCE_EXTRA_KEY
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source.TRENDING
import com.ottoboni.movies.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*

class ViewMoreViewModel @ViewModelInject constructor(
    private val genreRepository: IGenreRepository,
    private val showRepository: IShowRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val source = savedStateHandle.get<Source>(SOURCE_EXTRA_KEY)

    private val _shows = MutableLiveData<List<Show>>()
    val shows: LiveData<List<Show>> get() = _shows

    private val _actionOnShowClicked = SingleLiveEvent<Show?>()
    val actionOnShowClicked: LiveData<Show?> get() = _actionOnShowClicked

    init {
        loadShows()
    }

    private fun loadShows() = viewModelScope.launch {
        val genres = genreRepository.loadGenres()

        if (source == TRENDING)
            showRepository
                .fetchTrending()
                ?.apply { setGenres(genres) }
                ?.let(_shows::postValue)
        else
            showRepository
                .fetchPopular(1, Locale.getDefault().toLanguageTag())
                ?.apply { setGenres(genres) }
                ?.let(_shows::postValue)
    }

    fun onShowClicked(position: Int) = _actionOnShowClicked.call(_shows.value?.get(position))
}