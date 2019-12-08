package com.ottoboni.movies.domain.model.parser

interface Parser<in R, out M> {
    fun parse(remote: R): M
}