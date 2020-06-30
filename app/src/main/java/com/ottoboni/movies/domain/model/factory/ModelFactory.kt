package com.ottoboni.movies.domain.model.factory

interface ModelFactory<in I, out O> {
    fun make(remote: I): O
}