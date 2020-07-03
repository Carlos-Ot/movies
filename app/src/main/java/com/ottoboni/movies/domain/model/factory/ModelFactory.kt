package com.ottoboni.movies.domain.model.factory

interface ModelFactory<I, O> {
    fun make(remote: I): O
}
