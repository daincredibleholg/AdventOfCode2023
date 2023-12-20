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

    @Test
    fun `Given the demo input and treating the seeds as ranges then should return 46 (Part II)`() {
        val actual = almanac.lowestLocationForSeedRanges()

        assertEquals(46, actual)
    }
}
