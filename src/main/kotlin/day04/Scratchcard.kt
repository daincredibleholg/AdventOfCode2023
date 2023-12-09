package day04

class Scratchcard {
    fun calculatePoints(lines: List<String>) = getMatchingNumbers(lines).sum()

    private fun getMatchingNumbers(lines: List<String>) = buildList {
        for (line in lines) {
            val firstSplit = line.split(":")
            val winnersAndGuesses = firstSplit[1].split("|")
            val winners = winnersAndGuesses[0].split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }

            val guesses = winnersAndGuesses[1].split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }

            val intersection = winners.intersect(guesses)

            var result = 0
            for (i in 1..intersection.size) {
                result = if (i == 1) 1 else result * 2
            }

            add(result)
        }
    }
}


fun main() {
    val lines = {}.javaClass.classLoader?.getResource("day04/input.txt")?.readText()?.lines()!!

    val scratchcard = Scratchcard()
    val resultPart1 = scratchcard.calculatePoints(lines)

    println("Result day 04, part  I: $resultPart1")
}
