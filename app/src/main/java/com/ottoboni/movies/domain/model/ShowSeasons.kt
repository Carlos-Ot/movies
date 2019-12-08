package com.ottoboni.movies.domain.model

data class ShowSeasons(
    val show: Show,
    val seasons: List<Season>
)