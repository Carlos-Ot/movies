package com.ottoboni.movies.data.source.local.di

import android.content.Context
import com.ottoboni.movies.data.source.local.database.AppDatabase
import com.ottoboni.movies.data.source.local.database.dao.EpisodeDao
import com.ottoboni.movies.data.source.local.database.dao.GenreDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonWithEpisodesDao
import com.ottoboni.movies.data.source.local.database.dao.ShowDao
import com.ottoboni.movies.data.source.local.database.dao.ShowWithSeasonsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {
    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providesEpisodeDao(database: AppDatabase): EpisodeDao = database.episodeDao()

    @Provides
    @Singleton
    fun providesGenreDao(database: AppDatabase): GenreDao = database.genreDao()

    @Provides
    @Singleton
    fun providesSeasonDao(database: AppDatabase): SeasonDao = database.seasonDao()

    @Provides
    @Singleton
    fun providesSeasonWithEpisodesDao(database: AppDatabase): SeasonWithEpisodesDao =
        database.seasonWithEpisodesDao()

    @Provides
    @Singleton
    fun providesShowDao(database: AppDatabase): ShowDao = database.showDao()

    @Provides
    @Singleton
    fun providesShowWithSeasonsDao(database: AppDatabase): ShowWithSeasonsDao =
        database.showWithSeasonsDao()
}
