package days

import kotlin.math.pow
import kotlin.text.indexOf

class Day3(override val fileName: String) : Day {
    override fun solvePart1(): String {
        return extractLines().sumOf { findMaxJoltageRec(it.trim(), 2) }.toString()
    }

    override fun solvePart2(): String {
        return extractLines().sumOf { findMaxJoltageRec(it.trim(), 12) }.toString()
    }

    fun extractLines(): List<String> {
        return readFile().lines().filter { it.isNotBlank() }
    }

    @Deprecated("Use findMaxJoltageRec instead, much nicer")
    private fun findMaxJoltage(batteryBank: String): Int {
        // Leave at least one battery so we can have a range
        val leftMax = batteryBank.dropLast(1).max()
        val leftMaxIndex = batteryBank.indexOf(leftMax)

        val rightMax = batteryBank.substring(leftMaxIndex + 1).max().digitToInt()

        return leftMax.digitToInt() * 10 + rightMax
    }

    private fun findMaxJoltageRec(batteryBank: String, iterations: Int): Long {
        if (iterations == 0) return 0L

        val max = batteryBank.dropLast(iterations - 1).max()

        return max.digitToInt() * 10.0.pow((iterations - 1).toDouble())
            .toLong() + findMaxJoltageRec(batteryBank.substring(batteryBank.indexOf(max) + 1), iterations - 1)
    }
}