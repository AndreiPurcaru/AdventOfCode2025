package days

class Day5(override val fileName: String) : Day {
    override fun solvePart1(): String {
        val input = readFile()

        val ranges = input.substringBefore("\n\n").lines().map {
            it.substringBefore("-").toLong()..it.substringAfter("-").toLong()
        }

        val ingredientIds = input.substringAfter("\n\n").lines().map { it.toLong() }

        return ingredientIds.filter { ingredientId -> ranges.any { range -> ingredientId in range } }.size.toString()
    }

    override fun solvePart2(): String {
        val input = readFile()

        var ranges = input.substringBefore("\n\n").lines().map {
            it.substringBefore("-").toLong()..it.substringAfter("-").toLong()
        }

        var prevSize = ranges.size
        var newSize = 0

        while (prevSize != newSize) {
            ranges = ranges.sortedBy { it.first }.windowed(2).flatMap { nameMe(it) }
            prevSize = newSize
            newSize = ranges.size
        }

        return ranges.sumOf { it.last - it.first }.toString()

    }

    private fun nameMe(maybeTuple: List<LongRange>): List<LongRange> {
        if (maybeTuple.size != 2) return maybeTuple

        val (left, right) = maybeTuple

        // Check if ranges overlap or are adjacent
        if (left.last + 1 >= right.first && right.last + 1 >= left.first) {
            // They can be merged
            return listOf(minOf(left.first, right.first)..maxOf(left.last, right.last))
        }
        return maybeTuple
    }
}