package com.ottoboni.movies.domain.di

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.data.source.remote.model.ShowResponse
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.Show
import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import com.ottoboni.movies.domain.model.factory.GenreFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.domain.model.factory.SeasonFactory
import com.ottoboni.movies.domain.model.factory.ShowFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class FactoryModule {
    @Binds
    abstract fun bindEpisodeFactory(impl: EpisodeFactory): ModelFactory<EpisodeResponse, Episode>

    @Binds
    abstract fun bindGenreFactory(impl: GenreFactory): ModelFactory<GenreResponse, Genre>

    @Binds
    abstract fun bindSeasonFactory(impl: SeasonFactory): ModelFactory<SeasonResponse, Season>

    @Binds
    abstract fun bindShowFactory(impl: ShowFactory): ModelFactory<ShowResponse, Show>
}