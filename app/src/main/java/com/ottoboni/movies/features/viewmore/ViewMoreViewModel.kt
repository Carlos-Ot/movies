package com.ottoboni.movies.features.viewmore

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ottoboni.movies.connectivity.ConnectivityViewModel
import com.ottoboni.movies.domain.dispatchers.DispatcherMap
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.setGenres
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Companion.SOURCE_EXTRA_KEY
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source
import com.ottoboni.movies.features.viewmore.ViewMoreActivity.Source.TRENDING
import com.ottoboni.movies.util.SingleLiveEvent
import java.util.*

class ViewMoreViewModel @ViewModelInject constructor(
    private val genreRepository: IGenreRepository,
    private val showRepository: IShowRepository,
    dispatcherMap: DispatcherMap,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ConnectivityViewModel(dispatcherMap) {

    private val source = savedStateHandle.get<Source>(SOURCE_EXTRA_KEY)

    private val _shows = MutableLiveData<List<Show>>()
    val shows: LiveData<List<Show>> get() = _shows

    private val _actionOnShowClicked = SingleLiveEvent<Show?>()
    val actionOnShowClicked: LiveData<Show?> get() = _actionOnShowClicked

    val actionOnError: LiveData<Any> get() = actionOnGenericError

    val actionOnConnectivityError: LiveData<Any> get() = actionOnNoInternetError

    val actionOnNetworkError: LiveData<String?> get() = actionOnHttpError


    init {
        safeLaunch(::loadShows)
    }

    private suspend fun loadShows() {
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