package days

import days.Day1.Direction.LEFT
import days.Day1.Direction.RIGHT
import kotlin.math.abs

class Day1(override val fileName: String) : Day {
    override fun solvePart1(): String {
        val input = readFile().lines()
        val startingSafeRange = SafeRange(0, 100, 50)

        val temp = input.scan(startingSafeRange) { acc, instruction ->
            when (instruction[0]) {
                'R' -> acc.spin(instruction.substring(1).toInt(), RIGHT)
                'L' -> acc.spin(instruction.substring(1).toInt(), LEFT)
                else -> throw IllegalArgumentException("Invalid instruction: $instruction")
            }
        }
        return temp.count { it.currentPosition == 0 }.toString()

    }

    override fun solvePart2(): String {
        TODO("Not yet implemented")
    }

    private data class SafeRange (val lowerBound: Int, val upperBound: Int, val currentPosition: Int) {
        fun spin(amount: Int, direction: Direction): SafeRange {
            val directionModifier = when (direction) {
                LEFT -> -1
                RIGHT -> 1
            }

            val newPosition = (currentPosition + directionModifier * amount) % upperBound
            val boundedNewPosition = if (newPosition < 0) upperBound + newPosition else newPosition
            return SafeRange(lowerBound, upperBound, boundedNewPosition)
        }
    }

    private enum class Direction {
        LEFT, RIGHT
    }
}