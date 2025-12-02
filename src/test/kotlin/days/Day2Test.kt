package days

import kotlin.test.Test

class Day2Test {
    @Test
    fun solve() {
        val day2Example = Day2("Day2Example.txt")
        val day2 = Day2("Day2.txt")

        println(day2Example.solve())
        println(day2.solve())
    }
}