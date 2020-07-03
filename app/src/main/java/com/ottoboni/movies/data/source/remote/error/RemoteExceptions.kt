package com.ottoboni.movies.data.source.remote.error

class UnauthorizedException(override val message: String?) : Exception()

class NotFoundException(override val message: String?) : Exception()

class UnknownNetworkErrorException(override val message: String = "Unknown Error") : Exception()
