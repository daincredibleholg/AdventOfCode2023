package io.steinh.aoc.day07

class CamelCards(private val hands: List<Hand>) {

    fun totalWinnings(): Int {
        val cardsComparator = Comparator<Pair<Hand, HandType>> { o1, o2 ->
            handComparator(o1, o2)
        }

        return findTypeOfHands(hands).toList().sortedWith(cardsComparator).mapIndexed { index, pair ->
            (index + 1) * pair.first.bid
        }.sum()
    }


    private fun findTypeOfHands(hands: List<Hand>): Map<Hand, HandType> =
        hands.associateWith { hand ->
            findThatType(hand.cards)
        }

    private fun findThatType(cards: List<Card>): HandType {
        val groupedCards = cards.groupBy { it.marker }.values

        if (cards.contains(Card.JOKER) && groupedCards.size > 1) {
            // joke rule!
            return when(groupedCards.size) {
                2 -> HandType.FIVE_OF_A_KIND
                3 -> if (groupedCards.count { it.size == 3 } == 1)
                    HandType.FOUR_OF_A_KIND
                else
                    if (groupedCards.any { it.size == 2 && it.contains(Card.JOKER) })
                        HandType.FOUR_OF_A_KIND
                    else
                        HandType.FULL_HOUSE
                4 -> HandType.THREE_OF_A_KIND
                else -> HandType.ONE_PAIR
            }
        } else {
            return when (groupedCards.size) {
                1 -> HandType.FIVE_OF_A_KIND
                2 -> if (groupedCards.count { it.size == 4 } == 1) HandType.FOUR_OF_A_KIND else HandType.FULL_HOUSE
                3 -> if (groupedCards.count { it.size == 3 } == 1) HandType.THREE_OF_A_KIND else HandType.TWO_PAIR
                4 -> HandType.ONE_PAIR
                else -> HandType.HIGH_CARD
            }
        }

    }

}

fun main() {
    val input = {}.javaClass.classLoader?.getResource("day07/input.txt")?.readText()!!
    val gameWithoutJoker = CamelCards(inputToHands(input, false))

    val resultPartOne = gameWithoutJoker.totalWinnings()
    println("Result for day 07, part  I: $resultPartOne")

    val gameWithJoker = CamelCards(inputToHands(input, true))
    val resultPartTwo = gameWithJoker.totalWinnings()
    println("Result for day 07, part II: $resultPartTwo")
}
