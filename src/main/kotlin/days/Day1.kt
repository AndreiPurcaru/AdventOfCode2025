package days

import days.Day1.Direction.LEFT
import days.Day1.Direction.RIGHT
import kotlin.math.abs

class Day1(override val fileName: String) : Day {
    override fun solvePart1(): String {
        val intermediateSteps = rotateDial()
        return intermediateSteps.count { it.currentPosition == 0 }.toString()
    }

    override fun solvePart2(): String {
        val intermediateSteps = rotateDial()
        return intermediateSteps.last().fullRotations.toString()
    }

    private fun rotateDial(): List<SafeRange> {
        val input = readFile().lines()
        val startingSafeRange = SafeRange(0, 100, 50)

        val intermediateSteps = input.scan(startingSafeRange) { acc, instruction ->
            when (instruction[0]) {
                'R' -> acc.spin(instruction.substring(1).toInt(), RIGHT)
                'L' -> acc.spin(instruction.substring(1).toInt(), LEFT)
                else -> throw IllegalArgumentException("Invalid instruction: $instruction")
            }
        }
        return intermediateSteps
    }

    private data class SafeRange(
        val lowerBound: Int,
        val upperBound: Int,
        val currentPosition: Int,
        val fullRotations: Int = 0
    ) {
        fun spin(amount: Int, direction: Direction): SafeRange {
            val directionModifier = when (direction) {
                LEFT -> -1
                RIGHT -> 1
            }

            val adjustedAmount = (currentPosition + directionModifier * amount)
            val newPosition = adjustedAmount % upperBound

            // If we are moving left, the resulting % will be negative, so we need to bring it back in range
            val boundedNewPosition = if (newPosition < lowerBound) upperBound + newPosition else newPosition


            // If we are moving left, we need to add one full rotation if we are at the lower bound
            // because the division will round down. Don't add if the current position is 0
            // since it was already added by the previous rotation
            val newFullRotations =
                (if (adjustedAmount <= lowerBound && currentPosition != 0) 1 else 0) + abs(adjustedAmount) / upperBound


            return SafeRange(lowerBound, upperBound, boundedNewPosition, fullRotations + newFullRotations)
        }
    }

    private enum class Direction {
        LEFT, RIGHT
    }
}