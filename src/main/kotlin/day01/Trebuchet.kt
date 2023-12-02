package day01


class Trebuchet {

    fun String.replace(vararg pairs: Pair<String, String>): String =
        pairs.fold(this) { acc, (old, new) -> acc.replace(old, new, ignoreCase = true) }

    fun calibrate(input: List<String>): Int {

        val transformedStrings = transform(input)

        return transformedStrings.sumOf {
            s -> "${ s.first { it.isDigit() }}${ s.last { it.isDigit() }}".toInt()
        }

    }

    private fun transform(input: List<String>): List<String> {
        val regex = Regex("^(one|two|three|four|five|six|seven|eight|nine)")
        return buildList {
            for (line in input) {
                var transformed = ""
                for (i in line.indices) {
                    if (line[i].isDigit()) {
                        transformed += line[i]
                    } else {
                        transformed += when (regex.find(line.substring(i))?.value) {
                            "one" -> "1"
                            "two" -> "2"
                            "three" -> "3"
                            "four" -> "4"
                            "five" -> "5"
                            "six" -> "6"
                            "seven" -> "7"
                            "eight" -> "8"
                            "nine" -> "9"
                            else -> ""
                        }

                    }
                }
                add(transformed)
            }
        }

    }

}

fun main() {
    val input = {}.javaClass.classLoader?.getResource("day01/input.txt")?.readText()?.lines()
    val trebuchet = Trebuchet()

    val sum = trebuchet.calibrate(input!!)

    print("Result 1: $sum")
}

