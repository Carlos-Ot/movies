package com.ottoboni.movies.data.source.remote.di

import com.ottoboni.movies.data.source.remote.datasource.EpisodeRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.EpisodeRetrofitDataSource
import com.ottoboni.movies.data.source.remote.datasource.GenreRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.GenreRetrofitDataSource
import com.ottoboni.movies.data.source.remote.datasource.SeasonRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.SeasonRetrofitDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRemoteDataSource
import com.ottoboni.movies.data.source.remote.datasource.ShowRetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindEpisodeRemoteDataSource(impl: EpisodeRetrofitDataSource):
        EpisodeRemoteDataSource

    @Binds
    abstract fun bindGenreRemoteDataSource(impl: GenreRetrofitDataSource): GenreRemoteDataSource

    @Binds
    abstract fun bindSeasonRemoteDataSource(impl: SeasonRetrofitDataSource): SeasonRemoteDataSource

    @Binds
    abstract fun bindShowRemoteDataSource(impl: ShowRetrofitDataSource): ShowRemoteDataSource
}
