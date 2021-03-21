import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

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
        val rowCol: Pair<Int, Int>,
        var height: Int,
    )

    val T = nLineInts()[0]
    repeat(T) { t ->
        val (R, C) = nLineInts()
        val GRID = (0 until R).map { nLineInts() }

        var result = 0L

        val rowColInfoMap = (0 until R).flatMap { rowIndex ->
            (0 until C).map { colIndex ->
                rowIndex to colIndex to CellInfo(rowIndex to colIndex, GRID[rowIndex][colIndex])
            }
        }.toMap(HashMap())

        val heightInfoMap: HashMap<Int, HashSet<CellInfo>> = HashMap(rowColInfoMap.entries
            .groupBy { it.value.height }.mapValues { it.value.map { it.value }.toHashSet() })
        var currentScanHeight = heightInfoMap.keys.maxOrNull()!!

        while (heightInfoMap.containsKey(currentScanHeight)) {
            val infos = heightInfoMap[currentScanHeight]!!
            if (infos.isEmpty()) break;

            fun pullHeight(rowCol: Pair<Int, Int>) {
                val targetInfo = rowColInfoMap[rowCol]!!
                if (targetInfo.height < currentScanHeight - 1) {
                    heightInfoMap[targetInfo.height]!!.remove(targetInfo)
                    result += currentScanHeight - 1 - targetInfo.height
                    targetInfo.height = currentScanHeight - 1
                    heightInfoMap.getOrPut(targetInfo.height, ::HashSet).add(targetInfo)
                }
            }
            infos.forEach { (pair, cell) ->
                val (row, col) = pair
                if (row > 0) pullHeight(row - 1 to col)
                if (col > 0) pullHeight(row to col - 1)
                if (row < R - 1) pullHeight(row + 1 to col)
                if (col < C - 1) pullHeight(row to col + 1)
            }

            currentScanHeight--
        }


        wl("Case #${t + 1}: $result")
    }
}

