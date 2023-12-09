package io.steinh.aoc.day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CubeConundrumTest  {

    val cubeConundrum = CubeConundrum()
    val given = {}.javaClass.classLoader?.getResource("day02/demo-input.txt")?.readText()?.lines()

    @Test
    fun checkSumOfIds() {

        val bagLimits = listOf(
            CubeCount(12, "red"),
            CubeCount(13, "green"),
            CubeCount(14, "blue")
        )

        val actual = cubeConundrum.sumIds(given!!, bagLimits)

        assertEquals(14, actual)
    }

    @Test
    fun checkPowerOfTheFewest() {
        val actual = cubeConundrum.powerOfTheFewest(given!!)

        assertEquals(4470, actual)
    }
}
