package com.ottoboni.movies.domain.model.factory

import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre
import javax.inject.Inject

class GenreFactory @Inject constructor() : ModelFactory<GenreResponse, Genre> {
    override fun make(remote: GenreResponse) =
        Genre(
            id = remote.id,
            name = remote.name
        )
}