package com.ottoboni.movies.domain.di

import com.ottoboni.movies.domain.dispatchers.AppDispatcherMap
import com.ottoboni.movies.domain.dispatchers.DispatcherMap
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DispatchersModule {
    @Binds
    abstract fun bindDispatcherMap(impl: AppDispatcherMap): DispatcherMap
}