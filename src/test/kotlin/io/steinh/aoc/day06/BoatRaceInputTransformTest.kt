package io.steinh.aoc.day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoatRaceInputTransformTest {

    @Test
    fun `Given demo input should return expected result`() {
        val demoInput = {}.javaClass.classLoader?.getResource("day06/demo-input.txt")?.readText()!!
        val actual = interpretStats(demoInput)
        val expected = createDemoBoatRaceStats()

        assertEquals(expected, actual)
    }

}

fun createDemoBoatRaceStats() = BoatRaceStats(
    mapOf(
        7 to 9,
        15 to 40,
        30 to 200
    )
)
