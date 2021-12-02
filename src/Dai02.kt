import kotlin.streams.toList

fun main() {

    data class Command(val keyword: String, val value: Int)

    fun getInputAsCommands(fileName: String): List<Command> {
        return readInput(fileName).stream()
            .map {
                val (keyword, value) = it.split("\\s+".toRegex())
                Command(keyword, value.toInt())
            }
            .toList()
    }

    fun part1(input: List<Command>): Int {

        var horizontalPosition = 0
        var depth = 0

        for (command in input) {
            when (command.keyword) {
                "forward" -> horizontalPosition += command.value
                "down" -> depth += command.value
                "up" -> depth -= command.value
            }
        }

        return horizontalPosition * depth
    }

    fun part2(input: List<Command>): Int {

        var aim = 0
        var horizontalPosition = 0
        var depth = 0

        for (command in input) {
            when (command.keyword) {
                "forward" -> {
                    horizontalPosition += command.value
                    depth += aim * command.value
                }
                "down" -> aim += command.value
                "up" -> aim -= command.value
            }
        }

        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = getInputAsCommands("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = getInputAsCommands("Day02")
    println(part1(input))
    println(part2(input))
}