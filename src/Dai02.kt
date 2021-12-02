fun main() {

    fun part1(input: List<String>): Int {

        var horizontalPosition = 0
        var depth = 0

        for (line in input) {

            val split = line.split("\\s+".toRegex())

            when (split[0]) {
                "forward" -> horizontalPosition += split[1].toInt()
                "down" -> depth += split[1].toInt()
                "up" -> depth -= split[1].toInt()
            }
        }

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {

        var aim = 0
        var horizontalPosition = 0
        var depth = 0

        for (line in input) {

            val split = line.split("\\s+".toRegex())

            when (split[0]) {
                "forward" -> {
                    horizontalPosition += split[1].toInt()
                    depth += aim * split[1].toInt()
                }
                "down" -> aim += split[1].toInt()
                "up" -> aim -= split[1].toInt()
            }
        }

        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}