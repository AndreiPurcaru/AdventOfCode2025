package days

import kotlin.test.Test

class Day1Test {
    @Test
    fun solve() {
        val day1Example = Day1("Day1Example.txt")
        val day1 = Day1("Day1.txt")

        println(day1Example.solve())
        println(day1.solve())
    }
}