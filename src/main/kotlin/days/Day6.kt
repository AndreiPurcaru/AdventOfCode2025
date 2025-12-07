package days

import kotlin.text.toLong


class Day6(override val fileName: String) : Day {

    override fun solvePart1(): String {
        val combinedLines =
            readFile().lines().map { line -> line.split(" ").filter { it.isNotBlank() }.map { it.trim() } }

        val operationLine = combinedLines.last()
        val numberLines = combinedLines.dropLast(1)

        return numberLines[0].indices.sumOf { extractIntermediateResult(operationLine, numberLines, it) }.toString()
    }

    override fun solvePart2(): String {
        val combinedLines = readFile().lines()

        val operationLine = combinedLines.last().split(" ").filter { it.isNotBlank() }.map { it.trim() }
        val rawNumberLines = combinedLines.dropLast(1)

        val numbersWithNulls = rawNumberLines[0].indices.map { col -> extractNumberFromColumn(rawNumberLines, col) }

        val splitNumbers = numbersWithNulls.splitWhen { it == null }.map { it.filterNotNull() }

        return operationLine.withIndex().sumOf { (index, operation) ->
            when (operation) {
                "+" -> splitNumbers[index].sumOf { it }
                "*" -> splitNumbers[index].fold(1L) { acc, number -> acc * number }
                else -> throw IllegalArgumentException("Invalid operation: $operation")
            }
        }.toString()
    }

    private fun extractIntermediateResult(
        operationLine: List<String>,
        numberLines: List<List<String>>,
        col: Int
    ): Long {
        return when (operationLine[col]) {
            "+" -> numberLines.indices.sumOf { numberLines[it][col].toLong() }
            "*" -> numberLines.indices.drop(1)
                .fold(numberLines[0][col].toLong()) { acc, row -> acc * numberLines[row][col].toLong() }

            else -> throw IllegalArgumentException("Invalid operation: ${operationLine[col]}")
        }
    }

    private fun extractNumberFromColumn(rawNumberLines: List<String>, col: Int): Long? {
        val number = rawNumberLines.indices.map { row -> rawNumberLines[row][col] }.filter { it.isDigit() }
            .fold(0L) { acc, digit -> acc * 10 + digit.digitToInt() }
        return if (number == 0L) null else number
    }

    private fun <T> List<T>.splitWhen(predicate: (T) -> Boolean): List<List<T>> {
        return this.fold(listOf(emptyList<T>())) { acc, element ->
            if (predicate(element)) {
                if (acc.last().isEmpty()) acc else acc + listOf(emptyList())
            } else {
                acc.dropLast(1) + listOf(acc.last() + element)
            }
        }.filter { it.isNotEmpty() }
    }
}