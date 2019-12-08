package com.ottoboni.movies.domain.datasource.show

import com.ottoboni.movies.domain.datasource.BaseDataSource
import com.ottoboni.movies.domain.model.Show

interface ShowDataSource : BaseDataSource<Show> {
    suspend fun getShowsWithSeasons(): List<Show>
    suspend fun getShowWithSeasonsById(showId: Int): Show
    suspend fun fetchPopular(page: Int, region: String): List<Show>
    suspend fun fetchShow(showId: Int): Show
}