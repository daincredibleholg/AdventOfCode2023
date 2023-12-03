package day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CubeConundrumTest  {

    val cubeConundrum = CubeConundrum()

    @Test
    fun checkSumOfIds() {
        val given = {}.javaClass.classLoader?.getResource("day02/demo-input.txt")?.readText()?.lines()
        val bagLimits = listOf(
            CubeCount(12, "red"),
            CubeCount(13, "green"),
            CubeCount(14, "blue")
        )

        val actual = cubeConundrum.sumIds(given!!, bagLimits)

        assertEquals(14, actual)
    }
}
