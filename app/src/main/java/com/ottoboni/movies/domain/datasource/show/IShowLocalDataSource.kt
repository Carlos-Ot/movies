package com.ottoboni.movies.domain.datasource.show

import com.ottoboni.movies.domain.datasource.BaseLocalDataSource
import com.ottoboni.movies.domain.model.Show

interface IShowLocalDataSource : BaseLocalDataSource<Show> {
    suspend fun getShowsWithSeasons(): List<Show>
    suspend fun getShowWithSeasonById(showId: Int): Show
}