package com.ottoboni.movies.data.source.local.di

import com.ottoboni.movies.data.source.local.datasource.EpisodeLocalDataSource
import com.ottoboni.movies.data.source.local.datasource.EpisodeRoomDataSource
import com.ottoboni.movies.data.source.local.datasource.GenreLocalDataSource
import com.ottoboni.movies.data.source.local.datasource.GenreRoomDataSource
import com.ottoboni.movies.data.source.local.datasource.SeasonLocalDataSource
import com.ottoboni.movies.data.source.local.datasource.SeasonRoomDataSource
import com.ottoboni.movies.data.source.local.datasource.ShowLocalDataSource
import com.ottoboni.movies.data.source.local.datasource.ShowRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindEpisodeLocalDataSource(impl: EpisodeRoomDataSource): EpisodeLocalDataSource

    @Binds
    abstract fun bindGenreLocalDataSource(impl: GenreRoomDataSource): GenreLocalDataSource

    @Binds
    abstract fun bindSeasonLocalDataSource(impl: SeasonRoomDataSource): SeasonLocalDataSource

    @Binds
    abstract fun bindShowLocalDataSource(impl: ShowRoomDataSource): ShowLocalDataSource
}