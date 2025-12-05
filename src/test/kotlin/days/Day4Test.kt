package days

import kotlin.test.Test

class Day4Test {
    @Test
    fun solve() {
        val day4Example = Day4("Day4Example.txt")
        val day4 = Day4("Day4.txt")

        println(day4Example.solve())
        println(day4.solve())
    }
}