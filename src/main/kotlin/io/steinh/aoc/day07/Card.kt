package io.steinh.aoc.day07

enum class Card(val marker: String, val strength: Int) {
    ACE("A", 13),
    KING("K", 12),
    QUEEN("Q", 11),
    JACK("J", 10),
    TEN("T", 9),
    NINE("9", 8),
    EIGHT("8", 7),
    SEVEN("7", 6),
    SIX("6", 5),
    FIVE("5", 4),
    FOUR("4", 3),
    THREE("3", 2),
    TWO("2", 1),
    JOKER("JO", 0);

    companion object {
        fun fromMarker(marker: String): Card =
            entries.first { it.marker.lowercase().contentEquals(marker.lowercase()) }
    }
}

enum class HandType(val strength: Int) {
    HIGH_CARD(1),
    ONE_PAIR(2),
    TWO_PAIR(3),
    THREE_OF_A_KIND(4),
    FULL_HOUSE(5),
    FOUR_OF_A_KIND(6),
    FIVE_OF_A_KIND(7)
}

data class Hand(
    val cards: List<Card>,
    val bid: Int
)

fun inputToHands(input: String, jokerRule: Boolean = false): List<Hand> =
    "([1-9TJQKA]{5}) (\\d+)".toRegex().findAll(input).map { match ->
        Hand(
            match.groupValues[1]
                .map { marker ->
                    if (jokerRule && marker == 'J') Card.JOKER else Card.fromMarker(marker.toString())
                },
            match.groupValues[2].toInt()
        )
    }.toList()

fun handComparator(
    o1: Pair<Hand, HandType>?,
    o2: Pair<Hand, HandType>?
) =
    if (o1 == null && o2 == null) 0
    else if (o1 == null) -1
    else if (o2 == null) 1
    else if (o1.second.strength > o2.second.strength) 1
    else if (o1.second.strength < o2.second.strength) -1
    else if (o1.second.strength == o2.second.strength) {
        val unequalCards = o1.first.cards.zip(o2.first.cards).first { it.first.strength != it.second.strength }

        if (unequalCards.first.strength > unequalCards.second.strength) 1
        else -1
    } else 0
