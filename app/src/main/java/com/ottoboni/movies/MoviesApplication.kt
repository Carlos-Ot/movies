package com.ottoboni.movies

import android.app.Application
import com.ottoboni.movies.di.dataSourceModule
import com.ottoboni.movies.di.databaseModule
import com.ottoboni.movies.di.networkModule
import com.ottoboni.movies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MoviesApplication)
            modules(listOf(
                databaseModule,
                networkModule,
                dataSourceModule,
                viewModelModule
            ))
        }
    }
}