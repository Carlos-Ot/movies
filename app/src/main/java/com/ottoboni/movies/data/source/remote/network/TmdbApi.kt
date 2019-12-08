package com.ottoboni.movies.data.source.remote.network

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.model.GenreApiResponse
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/tv/list")
    fun genres(): Deferred<GenreApiResponse>

    @GET("tv/popular")
    fun popular(
        @Query("page") page: Int,
        @Query("region") region: String
    ): Deferred<ApiResponse>

    @GET("tv/{tv_id}")
    fun show(
        @Path("tv_id") id: Int
    ): Deferred<ShowResponse>

    @GET("tv/{tv_id}/season/{season_number}")
    fun season(
        @Path("tv_id") showId: Int,
        @Path("season_number") seasonNumber: Int
    ): Deferred<SeasonResponse>

    @GET("/tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    fun episode(
        @Path("tv_id") showId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Deferred<EpisodeResponse>
}