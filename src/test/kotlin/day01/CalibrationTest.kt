package day01

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class CalibrationTest {

    val calibration = Calibration()

    @Test
    fun checkDemoInput() {
        val input = {}.javaClass.classLoader?.getResource("day01/demo-input.txt")?.readText()?.lines()

        val actual = calibration.calibrate(input!!)

        assertEquals(actual, 142)
    }

    @Test
    fun checkDemoInputPart2() {
        val input = {}.javaClass.classLoader?.getResource("day01/demo-input-part2.txt")?.readText()?.lines()
        val actual = calibration.calibrate(input!!)

        assertEquals(actual, 281)
    }

}