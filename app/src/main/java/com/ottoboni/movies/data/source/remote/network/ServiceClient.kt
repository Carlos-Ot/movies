package com.ottoboni.movies.data.source.remote.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ottoboni.movies.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceClient {

    private const val API_KEY_PARAM = "api_key"

    fun getApiClient(retrofit: Retrofit): TmdbApi = retrofit.create(TmdbApi::class.java)

    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val url = request.url()

            val paramUrl = url.newBuilder()
                .addQueryParameter(API_KEY_PARAM, BuildConfig.API_KEY)
                .build()

            val requestBuilder = request.newBuilder()
                .url(paramUrl)

            val newRequest = requestBuilder.build()

            chain.proceed(newRequest)
        }
            .addInterceptor(logging)
            .followRedirects(false)

        return okHttpClientBuilder.build()
    }
}