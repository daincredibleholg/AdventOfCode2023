package io.steinh.aoc.day03

class GearRatios {
    fun addUpPartNumbers(lines: List<String>): Int {

        var result = 0
        for (lineIndex in lines.indices) {
            val line = lines[lineIndex]

            val numberMatches = "(\\d+)".toRegex().findAll(line)

            for (numberMatch in numberMatches) {
                var startPos = numberMatch.groups[0]!!.range.first()
                if (startPos > 0) {
                    startPos -= 1
                }
                val endPos = numberMatch.groups[0]!!.range.last() + 1

                val startLineId = if (lineIndex > 0) lineIndex - 1 else 0
                val endLineId = if (lineIndex < lines.lastIndex) lineIndex + 1 else lineIndex

                if (hasAdjacentSymbol(lines, startLineId, endLineId, startPos, endPos)) {
                    result += numberMatch.groups[0]!!.value.toInt()
                }


            }

        }

        return result
    }

    fun calculateGearRatioSum(lines: List<String>): Int {
        var result = 0

        for (lineIndex in lines.indices) {
            val line = lines[lineIndex]

            val asteriskMatches = "([*])".toRegex().findAll(line)

            for (match in asteriskMatches) {
                result += searchAndCalculate(lines, lineIndex, match.groups[0]!!.range)
            }
        }

        return result
    }

    private fun searchAndCalculate(lines: List<String>, lineIndex: Int, range: IntRange): Int {
        val minLineId = if (lineIndex > 0) lineIndex - 1 else 0
        val maxLineId = if (lineIndex < lines.lastIndex) lineIndex + 1 else lineIndex

        val foundNumbers = mutableListOf<Int>()
        for (i in minLineId..maxLineId) {
            val line = lines[i]

            val numberMatches = "(\\d+)".toRegex().findAll(line)

            for (match in numberMatches) {
                val matchRange = match.groups[0]!!.range
                val startAt = if (matchRange.first() > 0) matchRange.first() - 1 else 0
                val endAt = matchRange.last() + 1

                if ((startAt..endAt).contains(range.first())) {
                    foundNumbers.add(match.groups[0]!!.value.toInt())
                }

            }
        }

        if (foundNumbers.size == 2) {
            return foundNumbers.first() * foundNumbers.last()
        }
        return 0
    }

    private fun hasAdjacentSymbol(
        lines: List<String>,
        startLineId: Int,
        endLineId: Int,
        startPos: Int,
        endPos: Int
    ): Boolean {
        var result = false
        for (i in startLineId..endLineId) {
            val max = if (endPos >= lines[i].length) lines[i].length - 1 else endPos
            val subString = lines[i].substring(startPos..max)

            result = result || subString.any { it != '.' && !it.isDigit() }
        }

        return result
    }
}

fun main() {
    val gearRatios = GearRatios()

    val lines = {}.javaClass.classLoader?.getResource("day03/input.txt")?.readText()?.lines()!!
    val resultPart1 = gearRatios.addUpPartNumbers(lines)
    val resultPart2 = gearRatios.calculateGearRatioSum(lines)

    print ("Result Day 3, Part  I: $resultPart1\n")
    print ("Result Day 3, Part II: $resultPart2\n")
}
