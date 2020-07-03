package com.ottoboni.movies.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherMap @Inject constructor() : DispatcherMap {

    override val io: CoroutineDispatcher = Dispatchers.IO
    override val ui: CoroutineDispatcher = Dispatchers.Main
    override val computation: CoroutineDispatcher = Dispatchers.Default
}