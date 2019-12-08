package com.ottoboni.movies.data.repository

import com.ottoboni.movies.domain.datasource.show.IShowLocalDataSource
import com.ottoboni.movies.domain.datasource.show.IShowRemoteDataSource
import com.ottoboni.movies.domain.datasource.show.ShowDataSource
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.parser.SeasonParser
import com.ottoboni.movies.domain.model.parser.ShowParser

class ShowRepository(
    private val localDataSource: IShowLocalDataSource,
    private val remoteDataSource: IShowRemoteDataSource
) : ShowDataSource {
    override suspend fun getShowsWithSeasons(): List<Show> {
        return localDataSource.getShowsWithSeasons()
    }

    override suspend fun getShowWithSeasonsById(showId: Int): Show {
        return localDataSource.getShowWithSeasonById(showId)
    }

    override suspend fun fetchPopular(page: Int, region: String): List<Show> {
        val apiResponse = remoteDataSource.fetchPopularAsync(page, region).await()

        val popularShows = apiResponse.results.map { ShowParser.parse(it) }

        localDataSource.saveAll(*popularShows.toTypedArray())

        return popularShows
    }

    override suspend fun fetchShow(showId: Int): Show {
        val showResponse = remoteDataSource.fetchShowAsync(showId).await()

        val seasons = showResponse.seasons.map { SeasonParser.parse(it) }

        return ShowParser.parse(showResponse).apply { this.seasons = seasons }
    }

    override suspend fun save(domain: Show) {
        localDataSource.save(domain)
    }

    override suspend fun update(domain: Show) {
        localDataSource.update(domain)
    }

    override suspend fun delete(domain: Show) {
        localDataSource.delete(domain)
    }

    override suspend fun getAll(): List<Show> {
        return localDataSource.getAll()
    }

    override suspend fun getById(id: Int): Show {
        return localDataSource.getById(id)
    }
}