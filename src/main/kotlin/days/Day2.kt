package days

class Day2(override val fileName: String) : Day {
    override fun solvePart1(): String {
        return extractRanges().fold(0L) { acc, currentRange ->
            acc + currentRange.filter { isDoubled(it.toString()) }.sum()
        }.toString()
    }

    override fun solvePart2(): String {
        return extractRanges().fold(0L) { acc, currentRange ->
            acc + currentRange.filter { isRepeated(it.toString()) }.sum()
        }.toString()
    }

    private fun extractRanges(): List<LongRange> {
        return readFile().lines()[0].split(",")
            .map { rawPair -> Pair(rawPair.substringBefore('-').trim(), rawPair.substringAfter('-').trim()) }
            .map { stringPair -> stringPair.first.toLong()..stringPair.second.toLong() }
    }


    private fun isDoubled(numberString: String): Boolean {
        if (numberString.length % 2 != 0) return false
        return numberString.take(numberString.length / 2) == numberString.substring(numberString.length / 2)
    }

    private fun isRepeated(numberString: String): Boolean {
        for (i in 1..numberString.length / 2) {
            val chunks = numberString.chunked(i)
            if (chunks.toSet().size == 1) return true
        }
        return false
    }
}