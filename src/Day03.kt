enum class Criteria {
    OXYGEN, CO2
}

fun main() {

    fun binaryToDecimal(number: String): Long {

        var result: Long = 0
        var bit = 0
        var n: Int = number.length - 1

        while (n >= 0) {
            if (number[n] == '1') {
                result += (1 shl (bit))
            }
            n -= 1
            bit += 1
        }
        return result
    }

    fun part1(input: List<String>): Int {

        val numberOfColumns = input[0].toCharArray().size
        val sum = IntArray(numberOfColumns){0}

        for (line in input) {
            val lineAsNumbers = line.toCharArray().toTypedArray().map { Character.getNumericValue(it) }

            for (i in lineAsNumbers.indices) {
                sum[i] += lineAsNumbers[i]
            }
        }

        val numberOfLines = input.size
        val gamma = IntArray(numberOfColumns){0}
        val epsilon = IntArray(numberOfColumns){1}

        for (i in sum.indices) {
            if (sum[i] > numberOfLines / 2) {
                gamma[i] = 1
                epsilon[i] = 0
            }
        }

        val gammaDecimal = binaryToDecimal(gamma.joinToString(""))
        val epsilonDecimal = binaryToDecimal(epsilon.joinToString(""))

        return gammaDecimal.toInt() * epsilonDecimal.toInt()
    }

    fun getRating(input: List<String>, column: Int, criteria: Criteria): String {

        var sum = 0

        for (line in input) {
            val lineAsNumbers = line.toCharArray().toTypedArray().map { Character.getNumericValue(it) }
            sum += lineAsNumbers[column]
        }

        val numberOfLines = input.size
        var oxygen = if (criteria == Criteria.OXYGEN) 0 else 1

        if (sum >= numberOfLines.toDouble() / 2) {
            oxygen = if (criteria == Criteria.OXYGEN) 1 else 0
        }

        val filteredInput = input.filter { Character.getNumericValue(it[column]) == oxygen }

        return if (filteredInput.size > 1) {
            getRating(filteredInput, column + 1, criteria)
        } else {
            filteredInput[0]
        }
    }

    fun part2(input: List<String>): Int {
        val oxygen = getRating(input, 0, Criteria.OXYGEN)
        val co2 = getRating(input, 0, Criteria.CO2)
        val oxygenDecimal = binaryToDecimal(oxygen)
        val co2Decimal = binaryToDecimal(co2)
        return oxygenDecimal.toInt() * co2Decimal.toInt()
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}