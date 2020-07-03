package com.ottoboni.movies.data.source.remote.datasource

import com.ottoboni.movies.domain.model.factory.ModelFactory
import retrofit2.HttpException

abstract class RemoteDataSource(
    private val exceptionFactory: ModelFactory<HttpException, Exception>
) {
    protected suspend fun <T> safeCall(block: suspend () -> T) =
        try {
            block()
        } catch (exception: HttpException) {
            throw exceptionFactory.make(exception)
        }
}