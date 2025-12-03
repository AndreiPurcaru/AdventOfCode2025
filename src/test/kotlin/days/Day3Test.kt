package days

import kotlin.test.Test

class Day3Test {
    @Test
    fun solve() {
        val day3Example = Day3("Day3Example.txt")
        val day3 = Day3("Day3.txt")

        println(day3Example.solve())
        println(day3.solve())
    }
}