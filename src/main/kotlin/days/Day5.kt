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

        val ranges = input.substringBefore("\n\n").lines().map {
            it.substringBefore("-").toLong()..it.substringAfter("-").toLong()
        }

        return mergeAllRanges(ranges).sumOf { it.last - it.first + 1 }.toString()
    }

    private fun mergeAllRanges(ranges: List<LongRange>): List<LongRange> {
        return generateSequence(ranges to 0) { (currentRanges, previousSize) ->
            if (previousSize == currentRanges.size) return@generateSequence null

            val sortedRanges = currentRanges.sortedBy { it.first }

            val merged = if (previousSize % 2 == 1) {
                val rangesTryLeft = sortedRanges.chunked(2).flatMap { attemptMerge(it) }
                val rangesTryRight = listOf(sortedRanges[0]) + sortedRanges.drop(1).chunked(2).flatMap { attemptMerge(it) }
                if (rangesTryLeft.size < rangesTryRight.size) rangesTryLeft else rangesTryRight
            } else {
                sortedRanges.chunked(2).flatMap { attemptMerge(it) }
            }

            merged to sortedRanges.size
        }.last().first
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