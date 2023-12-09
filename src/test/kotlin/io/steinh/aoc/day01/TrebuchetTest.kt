package io.steinh.aoc.day01

import io.steinh.aoc.day01.Trebuchet
import kotlin.test.Test
import kotlin.test.assertEquals


class TrebuchetTest {

    val trebuchet = Trebuchet()

    @Test
    fun checkDemoInput() {
        val input = {}.javaClass.classLoader?.getResource("day01/demo-input.txt")?.readText()?.lines()

        val actual = trebuchet.calibrate(input!!)

        assertEquals(actual, 142)
    }

    @Test
    fun checkDemoInputPart2() {
        val input = {}.javaClass.classLoader?.getResource("day01/demo-input-part2.txt")?.readText()?.lines()
        val actual = trebuchet.calibrate(input!!)

        assertEquals(actual, 281)
    }

}
