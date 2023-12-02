package day01


class Calibration {

    fun String.replace(vararg pairs: Pair<String, String>): String =
        pairs.fold(this) { acc, (old, new) -> acc.replace(old, new, ignoreCase = true) }

    fun calibrate(input: List<String>): Int {

        return input.sumOf {
            s -> "${ s.first { it.isDigit() }}${ s.last { it.isDigit() }}".toInt()
        }

    }

}

fun main() {
    val input = {}.javaClass.classLoader?.getResource("day01/input.txt")?.readText()?.lines()
    val calibration = Calibration()

    var sum = calibration.calibrate(input!!)

    print("Result: $sum")
}

