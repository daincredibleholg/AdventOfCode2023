package io.steinh.aoc.day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoatRaceTest {

    private val boatRace = BoatRace(createDemoBoatRaceStats())

    @Test
    fun `Given demo input should return result from description`() {
        val actual = boatRace.calculateWaysToBeatTheRecord()

        assertEquals(288, actual)
    }

    @Test
    fun `Given all digits are part of one large number then the demo input should return 71503`() {
        val actual = boatRace.calculateForOneLargeRace()

        assertEquals(71503, actual)
    }
}
