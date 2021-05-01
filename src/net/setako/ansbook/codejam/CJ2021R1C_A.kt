import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil

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
private fun wl(vararg arr: Any, separator: String = " ") = wl(arr.joinToString(separator))

//endregion

fun main() {
    val (T) = nLineInts()
    repeat(T) { t ->
        val (N, K) = nLineInts();
        val PArr = nLineInts()

        val singleTake = ArrayList<Int>()
        val doubleTake = ArrayList<Int>()
        val sortedP = PArr.distinct().sorted()
        if (sortedP.first() != 1) {
            singleTake.add(sortedP.first() - 1)
        }
        if (sortedP.last() != K) {
            singleTake.add(K - sortedP.last())
        }

        sortedP.dropLast(1).zip(sortedP.drop(1)).forEach { (a, b) ->
            val space = b - a - 1
            doubleTake.add(space)
            singleTake.add(
                ceil(space.toDouble() / 2).toInt()
            )
        }


        wl(
            "Case #${t + 1}: ${
                ((doubleTake.maxOrNull() ?: 0).coerceAtLeast(
                    singleTake.sorted().takeLast(2).sum()
                )).toDouble() / K
            }"
        )
    }
}

