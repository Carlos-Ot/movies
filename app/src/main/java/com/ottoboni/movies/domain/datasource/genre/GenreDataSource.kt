package com.ottoboni.movies.domain.datasource.genre

import com.ottoboni.movies.domain.datasource.BaseDataSource
import com.ottoboni.movies.domain.model.Genre

interface GenreDataSource : BaseDataSource<Genre> {
    suspend fun loadGenres(): List<Genre>
}