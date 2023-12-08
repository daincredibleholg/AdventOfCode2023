package day04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScratchcardTest {

    val scratchcard = Scratchcard()
    val given = {}.javaClass.classLoader?.getResource("day04/demo-input.txt")?.readText()?.lines()


    @Test
    fun testScratchCardPoints() {
        val actual = scratchcard.calculatePoints(given!!)

        assertEquals(13, actual)
    }


}
