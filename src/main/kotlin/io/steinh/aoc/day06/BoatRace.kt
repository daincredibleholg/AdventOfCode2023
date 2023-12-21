package io.steinh.aoc.day06

class BoatRace(private val input: BoatRaceStats) {

    fun calculateWaysToBeatTheRecord(): Int {
        val possibleWins = buildList {
            input.timeToDistance.forEach { (time, distance) ->
                add(
                    buildList {
                        for (i in 1..time) {
                            if (((time - i).times(i)) > distance) {
                                add(i)
                            }
                        }
                    }
                )

            }
        }

        return possibleWins.map { it.size }.reduce { acc, next -> acc * next }
    }

    fun calculateForOneLargeRace(): Int {
        val longTime = input.timeToDistance.keys.map { it.toString() }.reduce { acc, next -> "$acc$next" }.toLong()
        val longDistance =
            input.timeToDistance.values.map { it.toString() }.reduce { acc, next -> "$acc$next" }.toLong()

        var count = 0

        for (i in 1..longTime) {
            if ((longTime - i).times(i) > longDistance) {
                count++
            }
        }

        return count
    }

}

data class BoatRaceStats(
    val timeToDistance: Map<Int, Int>
)

fun interpretStats(input: String): BoatRaceStats {
    val inputLines = input.split("\n")
    val times = "(\\d+)".toRegex().findAll(inputLines[0])
        .map {
            it.groupValues[0].toInt()
        }
        .toList()
    val distances = "(\\d+)".toRegex().findAll(inputLines[1])
        .map {
            it.groupValues[0].toInt()
        }
        .toList()


    return BoatRaceStats(
        timeToDistance = times.zip(distances) { t, d -> t to d }.toMap()
    )
}

fun main() {
    val input = {}.javaClass.classLoader?.getResource("day06/input.txt")?.readText()!!
    val boatRace = BoatRace(interpretStats(input))

    val partOneResult = boatRace.calculateWaysToBeatTheRecord()
    println("Result for day 06, part  I: $partOneResult")

    val partTwoResult = boatRace.calculateForOneLargeRace()
    println("Result for day 06, part II: $partTwoResult")
}
