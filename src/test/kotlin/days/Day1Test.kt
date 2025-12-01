package days

import kotlin.test.Test

class Day1Test {
    @Test
    fun solve() {
        val day1Example1 = Day1("Day1Example.txt")
        val day1 = Day1("Day1.txt")

        println(day1Example1.solve())
        println(day1.solve())
    }
}