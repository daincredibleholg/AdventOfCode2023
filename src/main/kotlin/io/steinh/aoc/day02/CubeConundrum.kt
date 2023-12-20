package io.steinh.aoc.day02

class CubeConundrum {

    fun sumIds(lines: List<String>, bagLimits: List<CubeCount>): Int {
        val processed = analyze(lines)

        var sum = 0
        for (game in processed) {
            var match = true

            for (entry in game.value) {
                for (limit in bagLimits) {
                    match = match && entry.any { it.color == limit.color && it.count <= limit.count }
                }
            }

            if (match) {
                print("Game ${game.key} is possible\n")
                sum += game.key
            }
        }

        return sum
    }

    fun powerOfTheFewest(lines: List<String>): Int {
        val processed = analyze(lines)

        val fewest = getFewest(processed)

        var result = 0

        for (game in fewest) {
            val value = game.value.map { it.count }.reduce { acc, i -> acc * i }

            result += value
        }

        return result
    }

    private fun getFewest(processed: Map<Int, List<List<CubeCount>>>): Map<Int, List<CubeCount>> {
        val result = mutableMapOf<Int, List<CubeCount>>()

        for (game in processed) {
            var red = 0
            var green = 0
            var blue = 0

            for (set in game.value) {
                for (entry in set) {
                    when (entry.color) {
                        "red" -> red = if (red < entry.count) entry.count else red
                        "green" -> green = if (green < entry.count) entry.count else green
                        "blue" -> blue = if (blue < entry.count) entry.count else blue
                    }

                }
            }

            result[game.key] = listOf(
                CubeCount(red, "red"),
                CubeCount(green, "green"),
                CubeCount(blue, "blue")
            )
        }

        return result
    }

    private fun analyze(lines: List<String>): Map<Int, List<List<CubeCount>>> {
        val result: MutableMap<Int, List<List<CubeCount>>> = mutableMapOf()
        for (line in lines) {
            val gameId = "Game (\\d+)".toRegex().find(line)!!.groupValues[1].toInt()

            val drawnSets = line.split(": ").last().split(";")

            val sets = buildList {
                for (set in drawnSets) {
                    add(
                        buildList {

                            var redCubes = 0
                            var greenCubes = 0
                            var blueCubes = 0
                            for (matches in "(\\d+) (red|green|blue)".toRegex().findAll(set)) {

                                val cnt = matches.groups[1]!!.value.toInt()

                                when (matches.groups[2]!!.value) {
                                    "red" -> redCubes += cnt
                                    "green" -> greenCubes += cnt
                                    "blue" -> blueCubes += cnt
                                }

                            }
                            add(CubeCount(redCubes, "red"))
                            add(CubeCount(blueCubes, "blue"))
                            add(CubeCount(greenCubes, "green"))
                        })
                }
            }

            result.put(gameId, sets)
        }


        return result
    }

}

data class CubeCount(
    val count: Int,
    val color: String,
)


fun main() {
    val input = {}.javaClass.classLoader?.getResource("day02/input.txt")?.readText()?.lines()!!

    val bagLimits = listOf(
        CubeCount(12, "red"),
        CubeCount(13, "green"),
        CubeCount(14, "blue")
    )

    val cubeConundrum = CubeConundrum()
    val result = cubeConundrum.sumIds(input, bagLimits)
    val resultPart2 = cubeConundrum.powerOfTheFewest(input)

    print("Solution for Day 2, Part  I: $result\n")
    print("Solution for Day 2, Part II: $resultPart2\n")
}
