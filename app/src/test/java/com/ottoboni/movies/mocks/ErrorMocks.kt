package com.ottoboni.movies.mocks

import com.ottoboni.movies.data.source.remote.model.ApiResponse
import io.mockk.every
import io.mockk.mockk
import retrofit2.Response

object ErrorMocks {

    private const val HTTP_UNAUTHORIZED_CODE = 401
    private const val HTTP_UNAUTHORIZED_MESSAGE =
        "Invalid API key: You must be granted a valid key."
    private const val HTTP_NOT_FOUND_CODE = 404
    private const val HTTP_NOT_FOUND_MESSAGE = "The resource you requested could not be found."


    fun mockErrorResponse(authorizationError: Boolean = false) = mockk<Response<ApiResponse>> {
        every { code() } returns if (authorizationError) HTTP_UNAUTHORIZED_CODE else HTTP_NOT_FOUND_CODE
        every { message() } returns if (authorizationError) HTTP_UNAUTHORIZED_MESSAGE else HTTP_NOT_FOUND_MESSAGE
    }
}