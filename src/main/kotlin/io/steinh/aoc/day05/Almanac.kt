package io.steinh.aoc.day05

class Almanac(private val sourceData: Input) {

    fun lowestLocation(): Long {
        val list = mutableListOf<Long>()

        sourceData.seeds.forEach {
            val soil = getSoilForSeed(it)
            val fertilizer = getFertilizerForSoil(soil)
            val water = getWaterForFertilizer(fertilizer)
            val light = getLightForWater(water)
            val temperature = getTemperatureForLight(light)
            val humidity = getHumidityForTemperature(temperature)

            list.add(getLocationForHumidity(humidity))
        }

        list.sort()

        return list.first()
    }

    fun lowestLocationForSeedRanges(): Long {
        var lowestLocationFound = 0L

        sourceData.seeds.chunked(2).forEach { seedRange ->
            (seedRange.first()..(seedRange.first() + seedRange.last())).forEach {
                val soil = getSoilForSeed(it)
                val fertilizer = getFertilizerForSoil(soil)
                val water = getWaterForFertilizer(fertilizer)
                val light = getLightForWater(water)
                val temperature = getTemperatureForLight(light)
                val humidity = getHumidityForTemperature(temperature)
                val location = getLocationForHumidity(humidity)

                lowestLocationFound =
                    if (lowestLocationFound == 0L || location < lowestLocationFound)
                        location
                    else lowestLocationFound
            }
        }


        return lowestLocationFound
    }

    private fun getSoilForSeed(seed: Long) =
        filterFromMapping(seed, sourceData.seedToSoilMappings)

    private fun getFertilizerForSoil(soil: Long) =
        filterFromMapping(soil, sourceData.soilToFertilizerMappings)

    private fun getWaterForFertilizer(fertilizer: Long) =
        filterFromMapping(fertilizer, sourceData.fertilizerToWaterMappings)

    private fun getLightForWater(water: Long) =
        filterFromMapping(water, sourceData.waterToLightMappings)

    private fun getTemperatureForLight(light: Long) =
        filterFromMapping(light, sourceData.lightToTemperatureMappings)

    private fun getHumidityForTemperature(temperature: Long) =
        filterFromMapping(temperature, sourceData.temperatureToHumidityMappings)

    private fun getLocationForHumidity(humidity: Long) =
        filterFromMapping(humidity, sourceData.humidityToLocationMappings)

    private fun filterFromMapping(id: Long, mappings: List<Mapping>): Long =
        mappings
            .filter {
                it.sourceRangeStart.contains(id)
            }.map {
                it.destinationRangeStart.first + (id - it.sourceRangeStart.first)
            }.ifEmpty {
                listOf(id)
            }.first()

}


fun main() {
    val rawInput = {}.javaClass.classLoader?.getResource("day05/input.txt")?.readText()!!
    val inputProcessor = AlmanacInputProcessor()
    val input = inputProcessor.transform(rawInput)

    val instance = Almanac(input)

    val resultOne = instance.lowestLocation()
    println("Result for day 05, part  I: $resultOne")

    val resultTwo = instance.lowestLocationForSeedRanges()
    println("Result for day 05, part II: $resultTwo")
}
