package com.ottoboni.movies.domain.model

import javax.inject.Inject

data class ShowSeasons @Inject constructor(
    val show: Show,
    val seasons: List<Season>
)