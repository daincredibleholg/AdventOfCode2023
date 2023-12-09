package day04

class Scratchcard {
    fun calculatePoints(lines: List<String>) = getMatchingNumbers(lines).sum()

    fun calculateNumberOfCardsWon(lines: List<String>): Int {
        val givenNumberOfCards = lines.size
        val numberOfCards = initCardStack(givenNumberOfCards)

        for (line in lines) {
            val firstSplit = line.split(":")
            val cardId = firstSplit[0].substringAfter("Card ").trim().toInt()
            println("Processing card #$cardId:")

            val instancesOfThisCard = numberOfCards[cardId]
            println("\texisting instances: $instancesOfThisCard")

            val numberOfMatches = extractWinningNumbers(firstSplit[1]).size
            println("\tno. matching numbers: $numberOfMatches")

            if (numberOfMatches == 0) {
                println("\tNo matches, continuing to next card. Done processing card #$cardId")
                continue
            }

            val start = cardId + 1
            if (start <= givenNumberOfCards) {
                val end =
                    if (cardId + numberOfMatches < givenNumberOfCards)
                        cardId + numberOfMatches
                    else
                        givenNumberOfCards

                println("\tWill add $instancesOfThisCard instances to cards ##" + (start..end))
                for (i in start..end) {
                    numberOfCards[i] = numberOfCards[i]!! + instancesOfThisCard!!
                    println("\t\tAdded $instancesOfThisCard to card #$i. This card has now ${numberOfCards[i]} " +
                            "instances.")
                }
            }
            println("\tDone processing card $cardId\n")
        }

        return numberOfCards
            .map { it.value }
            .sum()
    }

    private fun initCardStack(givenNumberOfCards: Int): MutableMap<Int, Int> {
        val result = mutableMapOf<Int, Int>()
        for (i in 1..givenNumberOfCards) {
            result[i] = 1
        }

        return result
    }

    private fun getMatchingNumbers(lines: List<String>) = buildList {
        for (line in lines) {
            val firstSplit = line.split(":")

            val intersection = extractWinningNumbers(firstSplit[1])

            var result = 0
            for (i in 1..intersection.size) {
                result = if (i == 1) 1 else result * 2
            }

            add(result)
        }
    }

    private fun extractWinningNumbers(matchesAndGuesses: String): Set<Int> {
        val winnersAndGuesses = matchesAndGuesses.split("|")
        val winners = winnersAndGuesses[0].split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

        val guesses = winnersAndGuesses[1].split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

        val intersection = winners.intersect(guesses.toSet())
        return intersection
    }
}


fun main() {
    val lines = {}.javaClass.classLoader?.getResource("day04/input.txt")?.readText()?.lines()!!

    val scratchcard = Scratchcard()
    val resultPart1 = scratchcard.calculatePoints(lines)
    val resultPart2 = scratchcard.calculateNumberOfCardsWon(lines)

    println("Result day 04, part  I: $resultPart1")
    println("Result day 04, part II: $resultPart2")
}
