package days

import kotlin.math.abs

class Day1(override val fileName: String) : Day {
    override fun solvePart1(): String {
        val input = readFile().lines()
        val startingSafeRange = SafeRange(0, 100, 50)

        val temp = input.scan(startingSafeRange) { acc, instruction ->
            when (instruction[0]) {
                'R' -> acc.spinRight(instruction.substring(1).toInt())
                'L' -> acc.spinLeft(instruction.substring(1).toInt())
                else -> throw IllegalArgumentException("Invalid instruction: $instruction")
            }
        }
        return temp.count { it.currentPosition == 0 }.toString()

    }

    override fun solvePart2(): String {
        TODO("Not yet implemented")
    }

    private data class SafeRange (val lowerBound: Int, val upperBound: Int, val currentPosition: Int) {
        fun spinRight(amount: Int) : SafeRange {
            val newPosition = (currentPosition + amount) % upperBound
            return SafeRange(lowerBound, upperBound, newPosition)
        }

        fun spinLeft(amount: Int) : SafeRange {
            val newPosition = (currentPosition - amount) % upperBound
            val boundedNewPosition = if (newPosition < 0) upperBound + newPosition else newPosition

            return SafeRange(lowerBound, upperBound, boundedNewPosition)
        }
    }
}