package io.steinh.aoc.day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AlmanacInputProcessorTest {

    private val processor = AlmanacInputProcessor()

    @Test
    fun `Given correct path, should read demo file correctly`() {
        val demoInput = {}.javaClass.classLoader?.getResource("day05/demo-input.txt")?.readText()
        val actual = processor.transform(demoInput!!)

        val expected = getDemoInput()

        assertEquals(expected, actual)
    }

}
