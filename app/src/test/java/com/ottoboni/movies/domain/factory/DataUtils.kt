package com.ottoboni.movies.domain.factory

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse

object DataUtils {

    val emptyEpisodeResponse = EpisodeResponse(
        episodeNumber = 1,
        airDate = "",
        name = "",
        overview = "",
        id = 6,
        stillPath = "",
        voteAverage = 10F
    )

    val notEmptyEpisodeResponse = EpisodeResponse(
        episodeNumber = 1,
        airDate = "2020-07-03",
        name = "episode name",
        overview = "episode overview",
        id = 6,
        stillPath = "path",
        voteAverage = 10F
    )

    val emptySeasonResponse = SeasonResponse(
        airDate = "",
        episodeCount = 10,
        id = 5,
        name = "",
        overview = "",
        posterPath = "",
        seasonNumber = 6,
        episodes = emptyList()
    )

    val notEmptySeasonResponse = SeasonResponse(
        airDate = "asas",
        episodeCount = 10,
        id = 5,
        name = "season",
        overview = "season overview",
        posterPath = "seasonPath",
        seasonNumber = 6,
        episodes = listOf(notEmptyEpisodeResponse)
    )

    val emptyGenreResponse = GenreResponse(
        id = 1,
        name = ""
    )

    val notEmptyGenreResponse = GenreResponse(
        id = 1,
        name = "Adventure"
    )

    val emptyShowResponse = ShowResponse(
        originalName = "",
        genreIds = emptyList(),
        name = "",
        popularity = 0F,
        originCountry = emptyList(),
        voteAverage = 0f,
        firstAirDate = "",
        backdropPath = "",
        originalLanguage = "",
        id = 0,
        voteCount = 0,
        overview = "",
        posterPath = "",
        seasons = emptyList(),
        genres = emptyList()
    )
    val notEmptyShowResponse = ShowResponse(
        originalName = "abc",
        genreIds = listOf(1, 2, 3),
        name = "abcd",
        popularity = 300F,
        originCountry = listOf("en"),
        voteAverage = 8.7F,
        firstAirDate = "2020-07-03",
        backdropPath = "path",
        originalLanguage = "en",
        id = 1,
        voteCount = 500,
        overview = "overview",
        posterPath = "path",
        seasons = listOf(notEmptySeasonResponse),
        genres = listOf(notEmptyGenreResponse)
    )
}