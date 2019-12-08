package com.ottoboni.movies.domain.model.parser

import com.ottoboni.movies.data.source.remote.model.GenreResponse
import com.ottoboni.movies.domain.model.Genre

object GenreParser : Parser<GenreResponse, Genre> {
    override fun parse(remote: GenreResponse): Genre {
        return Genre(
            id = remote.id,
            name = remote.name
        )
    }
}