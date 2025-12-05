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

        var currentSize = ranges.size
        var newSize = 0

        while (currentSize != newSize) {
            val sortedRanges = ranges.sortedBy { it.first }

            if (currentSize % 2 == 1) {
                val rangesTryLeft = sortedRanges.chunked(2).flatMap { attemptMerge(it) }
                val rangesTryRight =  listOf(sortedRanges[0]) + sortedRanges.drop(1).chunked(2).flatMap { attemptMerge(it) }
                if (rangesTryLeft.size < rangesTryRight.size) {
                    ranges = rangesTryLeft
                    currentSize = newSize
                    newSize = ranges.size
                } else {
                    ranges = rangesTryRight
                    currentSize = newSize
                    newSize = ranges.size
                }
                continue
            }

            ranges = sortedRanges.chunked(2).flatMap { attemptMerge(it) }
            currentSize = newSize
            newSize = ranges.size
        }

        return ranges.sumOf { it.last - it.first + 1 }.toString()

    }

    private fun attemptMerge(maybeTuple: List<LongRange>): List<LongRange> {
        if (maybeTuple.size != 2) return maybeTuple

        val (left, right) = maybeTuple

        // Check if ranges overlap or are adjacent
        if (left.last + 1 >= right.first) {
            return listOf(left.first..maxOf(left.last, right.last))
        }

        // Ranges are separate, keep both
        return maybeTuple
    }
}