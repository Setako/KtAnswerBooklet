import java.util.*
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
    val t = nLineInts()[0]

    repeat(t) {
        val n = nInt()
        val abArr = (1..n).map { nInt() to nInt() }
        val distArr = (listOf(0 to 0) + abArr).dropLast(1).zip(abArr)
            .map { (prev, next) -> next.first - prev.second }
        val tmArr = (1..n).map { nInt() }

        var now = 0
        tmArr.forEachIndexed { index, delay ->
            now += delay + distArr[index]
            if (index != n - 1) {
                now = (now + ceil(abArr[index].let { (a, b) -> (b - a).toFloat() / 2 }).toInt())
                    .coerceAtLeast(abArr[index].second)
            }
        }
        wl(now)
    }
}
