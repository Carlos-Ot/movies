package com.ottoboni.movies.data.source.remote.error

import com.ottoboni.movies.data.source.remote.model.ErrorResponse
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject
import retrofit2.HttpException

class RemoteExceptionFactory @Inject constructor() : ModelFactory<HttpException, Exception> {

    private val moshiAdapter =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(ErrorResponse::class.java)

    override fun make(remote: HttpException) = parseErrorResponse(remote)
        ?.let {
            when (remote.code()) {
                HTTP_UNAUTHORIZED -> UnauthorizedException(it.statusMessage)
                HTTP_NOT_FOUND -> NotFoundException(it.statusMessage)
                else -> UnknownNetworkErrorException()
            }
        }
        ?: UnknownNetworkErrorException()

    private fun parseErrorResponse(exception: HttpException) =
        runCatching { exception.response()?.errorBody()?.source()?.let(moshiAdapter::fromJson) }
            .getOrNull()

    companion object {
        private const val HTTP_UNAUTHORIZED = 401
        private const val HTTP_NOT_FOUND = 404
    }
}
