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

    @Test
    fun `Given a table of gift cards then returns amount of total cards won`() {
        val actual = scratchcard.calculateNumberOfCardsWon(given!!)

        assertEquals(30, actual)
    }

}
