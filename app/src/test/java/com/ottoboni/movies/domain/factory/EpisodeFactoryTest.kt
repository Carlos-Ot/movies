package com.ottoboni.movies.domain.factory

import com.ottoboni.movies.data.source.remote.model.EpisodeResponse
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.factory.EpisodeFactory
import com.ottoboni.movies.domain.model.factory.ModelFactory
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class EpisodeFactoryTest {

    private val emptyEpisodeResponse = DataUtils.emptyEpisodeResponse
    private val notEmptyEpisodeResponse = DataUtils.notEmptyEpisodeResponse

    private lateinit var factory: ModelFactory<EpisodeResponse, Episode>

    @Before
    fun setup() {
        factory = EpisodeFactory()
    }

    @Test
    fun `test empty fields are turned to null`() {
        val result = factory.make(emptyEpisodeResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertNull(result.airDate)
        assertNull(result.name)
        assertNull(result.overview)
        assertNull(result.stillPath)
    }

    @Test
    fun `test non-empty fields are parsed`() {
        val result = factory.make(notEmptyEpisodeResponse)

        assertNotNull(result)
        assertNotNull(result.id)
        assertEquals(notEmptyEpisodeResponse.airDate, result.airDate)
        assertEquals(notEmptyEpisodeResponse.name, result.name)
        assertEquals(notEmptyEpisodeResponse.overview, result.overview)
        assertEquals(notEmptyEpisodeResponse.stillPath, result.stillPath)
    }
}