package io.steinh.aoc.day05


fun getDemoInput() = Input(
    seeds = listOf(79, 14, 55, 13),
    seedToSoilMappings = listOf(
        Mapping(50L..51L, 98L..99L),
        Mapping(52L..99L, 50L..97L)
    ),
    soilToFertilizerMappings = listOf(
        Mapping(0L..36L, 15L..51L),
        Mapping(37L..38L, 52L..53L),
        Mapping(39L..53L, 0L..14L)
    ),
    fertilizerToWaterMappings = listOf(
        Mapping(49L..56L, 53L..60L),
        Mapping(0L..41L, 11L..52L),
        Mapping(42L..48L, 0L..6L),
        Mapping(57L..60L, 7L..10L)
    ),
    waterToLightMappings = listOf(
        Mapping(88L..94L, 18L..24L),
        Mapping(18L..87L, 25L..94L)
    ),
    lightToTemperatureMappings = listOf(
        Mapping(45L..67L, 77L..99L),
        Mapping(81L..99L, 45L..63L),
        Mapping(68L..80L, 64L..76L)
    ),
    temperatureToHumidityMappings = listOf(
        Mapping(0L..0L, 69L..69L),
        Mapping(1L..69L, 0L..68L)
    ),
    humidityToLocationMappings = listOf(
        Mapping(60L..96L, 56L..92L),
        Mapping(56L..59L, 93L..96L)
    )
)

