package com.ottoboni.movies.data.source.local.cache

interface ListCache<T> {

    var items: List<T>

    operator fun plusAssign(value: List<T>)

    operator fun plusAssign(value: T)

    fun clear()
}