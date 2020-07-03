package com.ottoboni.movies.data.source.remote.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ottoboni.movies.BuildConfig.TMDB_API_KEY
import com.ottoboni.movies.BuildConfig.TMDB_API_QUERY
import com.ottoboni.movies.BuildConfig.TMDB_BASE_URL
import com.ottoboni.movies.data.source.remote.TmdbApi
import com.ottoboni.movies.data.source.remote.converters.EnumConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitModule {
    @Singleton
    @Provides
    fun providesApiClient(retrofit: Retrofit): TmdbApi = retrofit.create(
        TmdbApi::class.java
    )

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshiAdapter: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addConverterFactory(EnumConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    internal fun providesOkHttpClient(
        @AuthInterceptor authInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(authInterceptor)
            addInterceptor(loggingInterceptor)

            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            followRedirects(false)
        }.build()

    @Singleton
    @Provides
    internal fun providesMoshiAdapter(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @AuthInterceptor
    @Provides
    internal fun providesAuthInterceptor(): Interceptor = Interceptor { chain ->
        chain.request()
            .newBuilder()
            .url(
                chain.request().url
                    .newBuilder()
                    .addQueryParameter(TMDB_API_QUERY, TMDB_API_KEY)
                    .build()
            )
            .build()
            .let { request -> chain.proceed(request) }
    }

    @Singleton
    @LoggingInterceptor
    @Provides
    internal fun providesLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.HEADERS }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor
