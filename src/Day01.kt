fun main() {

    fun part1(input: List<String>): Int {
        var increases = 0
        for (i in 0 until input.size-1) {
            if (input[i+1].toInt() > input[i].toInt()) {
                increases++
            }
        }
        return increases
    }

    fun part2(input: List<String>): Int {
        var increases = 0
        for (i in 0 until input.size-3) {
            val sum1 = input[i].toInt() + input[i+1].toInt() + input[i+2].toInt()
            val sum2 = input[i+1].toInt() + input[i+2].toInt() + input[i+3].toInt()
            if (sum2 > sum1) {
                increases++
            }
        }
        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
