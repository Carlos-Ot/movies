package com.ottoboni.movies.domain.factory

import com.ottoboni.movies.data.source.remote.model.SeasonResponse
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import com.ottoboni.movies.domain.model.factory.SeasonFactory
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SeasonFactoryTest {
    private val emptySeasonResponse = DataUtils.emptySeasonResponse
    private val notEmptySeasonResponse = DataUtils.notEmptySeasonResponse

    private lateinit var factory: ModelFactory<SeasonResponse, Season>

    @Before
    fun setup() {
        factory = SeasonFactory(EpisodeFactory())
    }

    @Test
    fun `test empty fields are turned to null`() {
        val result = factory.make(emptySeasonResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.episodeCount)
        assertNotNull(result.seasonNumber)
        assertNull(result.airDate)
        assertNull(result.name)
        assertNull(result.overview)
        assertNull(result.posterPath)
        assertNull(result.episodes)
    }

    @Test
    fun `test non-empty fields are parsed`() {
        val result = factory.make(notEmptySeasonResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNotNull(result.episodeCount)
        assertNotNull(result.seasonNumber)
        assertNotNull(result.episodes)
        assertEquals(notEmptySeasonResponse.airDate, result.airDate)
        assertEquals(notEmptySeasonResponse.name, result.name)
        assertEquals(notEmptySeasonResponse.overview, result.overview)
        assertEquals(notEmptySeasonResponse.posterPath, result.posterPath)
    }
}