package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.domain.model.Show

interface IShowRepository {
    suspend fun getShowsWithSeasons(): List<Show>?

    suspend fun getShowWithSeasonsById(showId: Int): Show?

    suspend fun fetchPopular(page: Int, region: String): List<Show>?

    suspend fun fetchShow(showId: Int): Show?

    suspend fun save(show: Show)

    suspend fun delete(show: Show)

    suspend fun getAll(): List<Show>?

    suspend fun getById(id: Int): Show?
}