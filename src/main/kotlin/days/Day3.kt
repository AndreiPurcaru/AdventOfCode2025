package days

import java.lang.Math.pow
import kotlin.math.pow
import kotlin.text.indexOf

class Day3(override val fileName: String) : Day {
    private val ITERATIONS = 12

    override fun solvePart1(): String {

        return extractLines().sumOf { findMaxJoltage(it.trim()) }.toString()
    }

    override fun solvePart2(): String {

        val list = extractLines().map { findMaxJoltageRec(it.trim()) }.toString()
        return extractLines().sumOf { findMaxJoltageRec(it.trim()) }.toString()
    }

    private fun findMaxJoltage(batteryBank: String): Int {
        // Leave at least one battery so we can have a range
        val leftMax = batteryBank.dropLast(1).max()
        val leftMaxIndex = batteryBank.indexOf(leftMax)

        val rightMax = batteryBank.substring(leftMaxIndex + 1).max().digitToInt()

        return leftMax.digitToInt() * 10 + rightMax
    }

    fun extractLines(): List<String> {
        return readFile().lines().filter { it.isNotBlank() }
    }

    private fun findMaxJoltageRec(batteryBank: String, foundMaxes: Int = 0): Long {
        if (foundMaxes == ITERATIONS) return 0L

        val max = batteryBank.dropLast(ITERATIONS - foundMaxes - 1).max()

        return max.digitToInt() * 10.0.pow((ITERATIONS - foundMaxes - 1).toDouble()).toLong() + findMaxJoltageRec(batteryBank.substring(batteryBank.indexOf(max) + 1), foundMaxes + 1)
    }
}