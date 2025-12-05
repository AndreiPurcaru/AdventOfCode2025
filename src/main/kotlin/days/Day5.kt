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

        return ranges.map { it.toSet()}.reduce { acc, set -> acc.union(set) }.size.toString()
    }

}