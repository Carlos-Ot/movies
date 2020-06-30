package com.ottoboni.movies.domain.di

import com.ottoboni.movies.domain.repository.EpisodeRepository
import com.ottoboni.movies.domain.repository.GenreRepository
import com.ottoboni.movies.domain.repository.IEpisodeRepository
import com.ottoboni.movies.domain.repository.IGenreRepository
import com.ottoboni.movies.domain.repository.ISeasonRepository
import com.ottoboni.movies.domain.repository.IShowRepository
import com.ottoboni.movies.domain.repository.SeasonRepository
import com.ottoboni.movies.domain.repository.ShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindEpisodeRepository(impl: EpisodeRepository): IEpisodeRepository

    @Binds
    abstract fun bindGenreRepository(impl: GenreRepository): IGenreRepository

    @Binds
    abstract fun bindSeasonRepository(impl: SeasonRepository): ISeasonRepository

    @Binds
    abstract fun bindShowRepository(impl: ShowRepository): IShowRepository
}