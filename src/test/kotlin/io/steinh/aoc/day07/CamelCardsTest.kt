package io.steinh.aoc.day07

import kotlin.test.Test
import kotlin.test.assertEquals

class CamelCardsTest {

    private val demoHand =
        listOf(
            Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
            Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE), 684),
            Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
            Hand(listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN), 220),
            Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE), 483)
        )
    private val demoJokerHand =
        listOf(
            Hand(listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING), 765),
            Hand(listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JOKER, Card.FIVE), 684),
            Hand(listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN), 28),
            Hand(listOf(Card.KING, Card.TEN, Card.JOKER, Card.JOKER, Card.TEN), 220),
            Hand(listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JOKER, Card.ACE), 483)
        )

    private val demoInput = {}.javaClass.classLoader?.getResource("day07/demo-input.txt")?.readText()!!

    @Test
    fun `Given demo input should return hand as described in text`() {
        val actual = inputToHands(demoInput)

        assertEquals(demoHand, actual)
    }

    @Test
    fun `Given demo input when joker rule is active then should return hand jokers instead of jacks`() {
        val actual = inputToHands(demoInput, jokerRule = true)

        assertEquals(demoJokerHand, actual)
    }

    @Test
    fun `Given the demo hand then returns result of 6440`() {
        val game = CamelCards(demoHand)
        val actual = game.totalWinnings()

        assertEquals(6440, actual)
    }

    @Test
    fun `Given the demo hand when following the Joke rule then the demo should result in 5905`() {
        val game = CamelCards(demoJokerHand)
        val actual = game.totalWinnings()

        assertEquals(5905, actual)

    }
}
