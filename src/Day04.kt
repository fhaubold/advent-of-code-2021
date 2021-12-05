class Board {
    val state = Array(5) { Array(5) { Pair(0, false) } }
    val rows = Array(5) { 0 }
    val columns = Array(5) { 0 }
}

fun main() {

    fun loadNumbers(input: String): List<Int> {
        return input.split(",").map { it.toInt() }
    }

    fun loadBoards(input: List<String>): MutableList<Board> {
        val boards: MutableList<Board> = mutableListOf()
        var boardIndex = -1
        var k = 0

        for (i in 1 until input.size) {
            if (input[i] == "") {
                boardIndex++
                boards.add(boardIndex, Board())
                k = 0
            } else {
                val row = input[i].trim().split("\\s+".toRegex()).map { it.trim() }.map { it.toInt() }
                for (j in row.indices) {
                    boards[boardIndex].state[k][j] = Pair(row[j], false)
                }
                k++
            }
        }
        return boards
    }

    fun checkWin(board: Board, row: Int, column: Int): Boolean {
        return (board.rows[row] == 5) or (board.columns[column] == 5)
    }

    fun playGame(numbers: List<Int>, boards: MutableList<Board>): Pair<Board, Int> {
        for (number in numbers) {
            for (board in boards) {
                for (row in board.state.indices) {
                    for (column in board.state[row].indices) {
                        if (board.state[row][column].first == number) {
                            board.state[row][column] = Pair(board.state[row][column].first, true)
                            board.rows[row]++
                            board.columns[column]++
                            if (checkWin(board, row, column)) return Pair(board, number)
                        }
                    }
                }
            }
        }
        throw Error("No winner")
    }

    fun playGameUntilLastBoardWins(numbers: List<Int>, boards: MutableList<Board>): Pair<Board, Int> {
        val winners = mutableSetOf<Board>()
        for (number in numbers) {
            for (board in boards) {
                for (row in board.state.indices) {
                    for (column in board.state[row].indices) {
                        if (board.state[row][column].first == number) {
                            board.state[row][column] = Pair(board.state[row][column].first, true)
                            board.rows[row]++
                            board.columns[column]++
                            if (checkWin(board, row, column)) {
                                winners.add(board)
                                if (winners.size == boards.size) return Pair(board, number)
                            }
                        }
                    }
                }
            }
        }
        throw Error("No last winner")
    }

    fun sumUnmarked(board: Board): Int {
        var sum = 0
        for (row in board.state) {
            for (value in row) {
                if (!value.second) sum += value.first
            }
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        val numbers = loadNumbers(input[0])
        val boards: MutableList<Board> = loadBoards(input)
        val result = playGame(numbers, boards)
        val sum = sumUnmarked(result.first)
        return sum * result.second
    }

    fun part2(input: List<String>): Int {
        val numbers = loadNumbers(input[0])
        val boards: MutableList<Board> = loadBoards(input)
        val result = playGameUntilLastBoardWins(numbers, boards)
        val sum = sumUnmarked(result.first)
        return sum * result.second
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}