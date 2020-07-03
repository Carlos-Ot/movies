package com.ottoboni.movies.features.showdetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.toPlainText
import com.ottoboni.movies.features.showdetails.ShowDetailsActivity.Companion.SHOW_EXTRA_KEY
import kotlin.math.ceil

class ShowDetailsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _show = savedStateHandle.getLiveData<Show>(SHOW_EXTRA_KEY)

    val name = _show.map { it.name }
    val backdropUrl = _show.map { it.backdropUrl }
    val posterUrl = _show.map { it.posterUrl }
    val popularityValue = _show.map { show -> show.voteAverage?.times(10)?.let { ceil(it) } }
    val popularityText = popularityValue.map { it?.toInt().toString() }
    val genres = _show.map { it.genres?.toPlainText() }
    val overview = _show.map { it.overview }
}
