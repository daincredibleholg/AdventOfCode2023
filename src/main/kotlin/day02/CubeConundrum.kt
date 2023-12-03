package day02

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

    private fun analyze(lines: List<String>): Map<Int, List<List<CubeCount>>> {
        val result: MutableMap<Int, List<List<CubeCount>>> = mutableMapOf()
        for (line in lines) {
            val gameId = "Game (\\d+)".toRegex().find(line)!!.groupValues[1].toInt()

            val drawnSets = line.split(": ").last.split(";")

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
                            "blue" -> blueCubes  += cnt
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

    print ("Solution for Day 2, Part I: $result")
}
