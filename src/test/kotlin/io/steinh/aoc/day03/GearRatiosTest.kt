package io.steinh.aoc.day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GearRatiosTest {

    val gearRatios = GearRatios()
    val given = {}.javaClass.classLoader?.getResource("day03/demo-input.txt")?.readText()?.lines()!!

    @Test
    fun testPartNumberAdding() {
        val actual = gearRatios.addUpPartNumbers(given)

        assertEquals(4361, actual)
    }

    @Test
    fun testGearRatioSum() {
        val actual = gearRatios.calculateGearRatioSum(given)

        assertEquals(467835, actual)
    }
}
