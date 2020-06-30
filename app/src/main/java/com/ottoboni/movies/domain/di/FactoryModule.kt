package com.ottoboni.movies.domain.di

import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import com.ottoboni.movies.domain.model.factory.GenreFactory
import com.ottoboni.movies.domain.model.factory.SeasonFactory
import com.ottoboni.movies.domain.model.factory.ShowFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object FactoryModule {
    @Provides
    fun providesEpisodeFactory() = EpisodeFactory()

    @Provides
    fun providesGenreFactory() = GenreFactory()

    @Provides
    fun providesSeasonFactory(episodeFactory: EpisodeFactory) =
        SeasonFactory(episodeFactory)

    @Provides
    fun providesShowFactory() = ShowFactory()
}