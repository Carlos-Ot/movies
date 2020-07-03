package com.ottoboni.movies.features.viewmore

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.ottoboni.movies.connectivity.ConnectivityViewModel
import com.ottoboni.movies.domain.dispatchers.DispatcherMap
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.setGenres
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import com.ottoboni.movies.domain.repository.ShowRepository.Companion.PAGE_SIZE
import com.ottoboni.movies.domain.repository.ShowRepository.Companion.STARTING_PAGE
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

    val shows: LiveData<PagedList<Show>> = buildPagedListLiveData()

    private val _actionOnShowClicked = SingleLiveEvent<Show?>()
    val actionOnShowClicked: LiveData<Show?> get() = _actionOnShowClicked

    val actionOnError: LiveData<Any> get() = actionOnGenericError

    val actionOnConnectivityError: LiveData<Any> get() = actionOnNoInternetError

    val actionOnNetworkError: LiveData<String?> get() = actionOnHttpError

    fun onShowClicked(position: Int) = _actionOnShowClicked.call(shows.value?.get(position))

    private fun buildPagedListLiveData() = LivePagedListBuilder(
        object : DataSource.Factory<Int, Show>() {
            override fun create() = ShowPagingDataSource(showRepository, genreRepository)
        },
        Config(
            PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSizeHint = PAGE_SIZE
        )
    ).build()

    private inner class ShowPagingDataSource(
        private val showRepository: IShowRepository,
        private val genreRepository: IGenreRepository
    ) : PageKeyedDataSource<Int, Show>() {

        var initialLoad: Pair<LoadInitialParams<Int>, LoadInitialCallback<Int, Show>>? = null
            private set
        private var laterLoad: Pair<LoadParams<Int>, LoadCallback<Int, Show>>? = null

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) = Unit

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Show>
        ) = safeLaunch {
            saveState(params, callback)

            val genres = genreRepository.loadGenres()

            callback.onResult(load(STARTING_PAGE, genres), null, STARTING_PAGE + 1)

            resetState()
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) =
            safeLaunch {
                saveState(params, callback)

                val genres = genreRepository.loadGenres()

                callback.onResult(load(params.key, genres), params.key + 1)

                resetState()
            }

        private fun saveState(
            parameters: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Show>
        ) {
            initialLoad = parameters to callback
        }

        private fun saveState(parameters: LoadParams<Int>, callback: LoadCallback<Int, Show>) {
            laterLoad = parameters to callback
        }

        private suspend fun load(page: Int, genres: List<Genre>?) =
            if (source == TRENDING) showRepository
                .fetchTrending(page)
                ?.apply { setGenres(genres) }
                .orEmpty()
                .toMutableList()
            else showRepository
                .fetchPopular(page, Locale.getDefault().toLanguageTag())
                .orEmpty()
                .toMutableList()

        private fun resetState() {
            initialLoad = null
            laterLoad = null
        }
    }
}