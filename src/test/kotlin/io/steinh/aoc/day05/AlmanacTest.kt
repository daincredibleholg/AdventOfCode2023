package io.steinh.aoc.day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AlmanacTest {

    private val almanac = Almanac(getDemoInput())

    @Test
    fun `Given the demo input then should return 35`() {
        val actual = almanac.lowestLocation()

        assertEquals(35, actual)
    }
}
