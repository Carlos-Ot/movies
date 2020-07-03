package com.ottoboni.movies.data.source.local.di

import com.ottoboni.movies.data.source.local.cache.GenreCache
import com.ottoboni.movies.data.source.local.cache.ListCache
import com.ottoboni.movies.data.source.local.cache.ShowCache
import com.ottoboni.movies.domain.model.Genre
import com.ottoboni.movies.domain.model.Show
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class CacheModule {
    @Binds
    abstract fun bindShowCache(impl: ShowCache): ListCache<Show>

    @Binds
    abstract fun bindGenreCache(impl: GenreCache): ListCache<Genre>
}
