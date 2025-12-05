package days

class Day4(override val fileName: String) : Day {
    override fun solvePart1(): String {
        val matrix = extractMatrix()

        return matrix.indices.sumOf { i ->
            matrix[i].indices.filter { checkRemovable(matrix, i, it) }.size
        }.toString()
    }


    override fun solvePart2(): String {
        val matrix = extractMatrix()
        var removed = 1
        var result = 0

        while (removed != 0) {
            removed = matrix.indices.sumOf { i ->
                matrix[i].indices.sumOf { checkAndRemove(matrix, i, it) }
            }
            result += removed
        }
        return result.toString()
    }

    private fun extractMatrix(): Array<Array<Char>> =
        readFile().lines().map { line -> line.map { it }.toTypedArray() }.toTypedArray()

    private fun checkRemovable(matrix: Array<Array<Char>>, i: Int, j: Int): Boolean {
        return if (matrix[i][j] == '@') {
            findSurroundingRolls(matrix, i, j) < 4
        } else {
            false
        }
    }

    private fun findSurroundingRolls(matrix: Array<Array<Char>>, i: Int, j: Int): Int {
        return Direction.entries.sumOf { (x, y) ->
            if (i + x !in matrix.indices || j + y !in matrix[i].indices) 0
            else if (matrix[i + x][j + y] == '.') 0
            else 1
        }
    }

    private fun checkAndRemove(matrix: Array<Array<Char>>, i: Int, j: Int): Int {
        if (checkRemovable(matrix, i, j)) {
            matrix[i][j] = '.'
            return 1
        } else return 0
    }

    private enum class Direction(val x: Int, val y: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP_LEFT(-1, -1),
        UP_RIGHT(1, -1),
        DOWN_LEFT(-1, 1),
        DOWN_RIGHT(1, 1);

        operator fun component1() = x
        operator fun component2() = y
    }
}