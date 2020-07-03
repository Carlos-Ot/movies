package com.ottoboni.movies.data.source.remote

import com.ottoboni.movies.BuildConfig.TMDB_API_VERSION
import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.data.source.remote.model.enums.MediaType
import com.ottoboni.movies.data.source.remote.model.enums.TimeWindow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(): GenreApiResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("page") page: Int,
        @Query("region") region: String
    ): ApiResponse?

    @GET("$TMDB_API_VERSION/trending/{media_type}/{time_window}")
    suspend fun fetchTrendingShowsAsync(
        @Path("media_type") mediaType: MediaType,
        @Path("time_window") timeWindow: TimeWindow,
        @Query("page") page: Int
    ): ApiResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): ShowResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonAsync(
        @Path("tv_id") showId: Int,
        @Path("season_number") seasonNumber: Int
    ): SeasonResponse?

    @GET("$TMDB_API_VERSION//tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    suspend fun fetchEpisodeAsync(
        @Path("tv_id") showId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): EpisodeResponse?
}