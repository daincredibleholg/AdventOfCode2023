package io.steinh.aoc.day05

class AlmanacInputProcessor {

    companion object {
        const val LENGTH_POSITION = 3
    }

    fun transform(input: String): Input {
        return Input(
            extractSeeds(input),
            extractBlockFor("seed-to-soil", input),
            extractBlockFor("soil-to-fertilizer", input),
            extractBlockFor("fertilizer-to-water", input),
            extractBlockFor("water-to-light", input),
            extractBlockFor("light-to-temperature", input),
            extractBlockFor("temperature-to-humidity", input),
            extractBlockFor("humidity-to-location", input)
        )
    }

    private fun extractSeeds(input: String): List<Int> {
        val spaceSeperatedSeeds = "^seeds: ([\\d ]+)\n".toRegex().find(input) ?: return emptyList()

        return spaceSeperatedSeeds.groupValues[1].split(" ").map { it.toInt() }
    }

    private fun extractBlockFor(prefix: String, input: String): List<Mapping> {
        val rawMappings = "$prefix map:.*([\\d \\n]+)".toRegex().findAll(input)

        return rawMappings.flatMap {
            "(\\d+) (\\d+) (\\d+)\\n?".toRegex().findAll(it.groupValues[1])
                .map { mappings -> extractMapping(mappings) }
        }.toList()
    }

    private fun extractMapping(matchResult: MatchResult): Mapping {
        var length = matchResult.groupValues[LENGTH_POSITION].toInt()
        if (length > 0) {
            length -= 1
        }

        val destinationRangeStart = matchResult.groupValues[1].toInt()
        val sourceRangeStart = matchResult.groupValues[2].toInt()
        return Mapping(destinationRangeStart..(destinationRangeStart+length),
            sourceRangeStart..(sourceRangeStart+length))
    }

}

data class Mapping(
    val destinationRangeStart: IntRange,
    val sourceRangeStart: IntRange,
)

data class Input(
    val seeds: List<Int>,
    val seedToSoilMappings: List<Mapping>,
    val soilToFertilizerMappings: List<Mapping>,
    val fertilizerToWaterMappings: List<Mapping>,
    val waterToLightMappings: List<Mapping>,
    val lightToTemperatureMappings: List<Mapping>,
    val temperatureToHumidityMappings: List<Mapping>,
    val humidityToLocationMappings: List<Mapping>,
)
