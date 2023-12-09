package io.steinh.aoc.day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AlmanacInputProcessorTest {

    private val processor = AlmanacInputProcessor()

    @Test
    fun `Given correct path, should read demo file correctly`() {
        val demoInput = {}.javaClass.classLoader?.getResource("day05/demo-input.txt")?.readText()
        val actual = processor.transform(demoInput!!)

        val expected = Input(
            seeds = listOf(79, 14, 55, 13),
            seedToSoilMappings = listOf(
                Mapping(50..51, 98..99),
                Mapping(52..99, 50..97)
            ),
            soilToFertilizerMappings = listOf(
                Mapping(0..36, 15..51),
                Mapping(37..38, 52..53),
                Mapping(39..53, 0..14)
            ),
            fertilizerToWaterMappings = listOf(
                Mapping(49..56, 53..60),
                Mapping(0..41, 11..52),
                Mapping(42..48, 0..6),
                Mapping(57..60, 7..10)
            ),
            waterToLightMappings = listOf(
                Mapping(88..94, 18..24),
                Mapping(18..87, 25..94)
            ),
            lightToTemperatureMappings = listOf(
                Mapping(45..67, 77..99),
                Mapping(81..99,45..63),
                Mapping(68..80, 64..76)
            ),
            temperatureToHumidityMappings = listOf(
                Mapping(0..0, 69..69),
                Mapping(1..69, 0..68)
            ),
            humidityToLocationMappings = listOf(
                Mapping(60..96, 56..92),
                Mapping(56..59, 93..96)
            )
        )

        assertEquals(expected, actual)
    }

}
