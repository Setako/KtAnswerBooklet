import java.util.*

//region Utils Declaration
private val output = System.out
private val input = Scanner(System.`in`)

private fun nInt() = input.nextInt()
private fun nLong() = input.nextLong()
private fun nDouble() = input.nextDouble()
private fun nWord() = input.next()
private fun nBigInteger() = input.nextBigInteger()

private fun nLine() = input.nextLine()
private fun nLineWords() = nLine().split(" ")
private fun nLineInts() = nLineWords().map { it.toInt() }
private fun nLineLongs() = nLineWords().map { it.toLong() }
private fun nLineDoubles() = nLineWords().map { it.toDouble() }
private fun nLineBigIntegers() = nLineWords().map { it.toBigInteger() }

private fun w(string: String) = output.print(string)
private fun w(number: Number) = output.print(number.toString())
private fun w(arr: Iterable<*>, separator: String = " ") = w(arr.joinToString(separator))
private fun wl() = output.println()
private fun wl(string: String) = output.println(string)
private fun wl(number: Number) = output.println(number.toString())
private fun wl(arr: Iterable<*>, separator: String = " ") = wl(arr.joinToString(separator))
//endregion

fun main() {
    data class CellInfo(
        var left: Int = 0,
        var right: Int = 0,
        var top: Int = 0,
        var bottom: Int = 0
    )

    val T = nLineInts()[0]
    repeat(T) { t ->
        val (R, C) = nLineInts()
        val GRID = (0 until R).map { nLineInts().map { it == 1 } }

        val gridInfo = (0 until R).map { (0 until C).map { CellInfo() } }

        GRID.forEachIndexed { rowIndex, row ->
            val leftCells = arrayListOf<CellInfo>()

            row.forEachIndexed { colIndex, cell ->
                val cellInfo = gridInfo[rowIndex][colIndex]
                if (cell) {
                    leftCells += cellInfo
                    cellInfo.left = leftCells.size
                } else {
                    leftCells.forEachIndexed { i, it ->
                        it.right = leftCells.size - i
                    }
                    leftCells.clear()
                }
            }
            leftCells.forEachIndexed { i, it ->
                it.right = leftCells.size - i
            }
            leftCells.clear()
        }


        (0 until C).forEach { colIndex ->
            val topCells = arrayListOf<CellInfo>()
            (0 until R).forEach { rowIndex ->
                val cell = GRID[rowIndex][colIndex]
                val cellInfo = gridInfo[rowIndex][colIndex]
                if (cell) {
                    topCells += cellInfo
                    cellInfo.top = topCells.size
                } else {
                    topCells.forEachIndexed { i, it ->
                        it.bottom = topCells.size - i
                    }
                    topCells.clear()
                }
            }
            topCells.forEachIndexed { i, it ->
                it.bottom = topCells.size - i
            }
            topCells.clear()
        }

        val ans = gridInfo.flatten().map {
            listOf(
                (Math.min(it.top / 2, it.left) - 1),
                (Math.min(it.top / 2, it.right) - 1),
                (Math.min(it.right / 2, it.top) - 1),
                (Math.min(it.right / 2, it.bottom) - 1),
                (Math.min(it.bottom / 2, it.left) - 1),
                (Math.min(it.bottom / 2, it.right) - 1),
                (Math.min(it.left / 2, it.top) - 1),
                (Math.min(it.left / 2, it.bottom) - 1),
            ).filter { it > 0 }.sum()
        }.sum()



        wl("Case #${t + 1}: ${ans}")
    }
}

