import java.math.BigInteger
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
        val (Y) = nLineBigIntegers()

        var result = BigInteger("9999999999999999999999999999999999999999")
        var next = BigInteger.ONE
        while (next < BigInteger("100000")){
            var current = next.add(BigInteger.ONE)
            var currentStr = BigInteger(next.toString() + current)
            while (currentStr <= Y){
                current = current.add(BigInteger.ONE)
                currentStr = BigInteger(currentStr.toString() + current)
            }
            result = currentStr.coerceAtMost(result)
            next++
        }

        wl(
            "Case #${t + 1}: ${result}"
        )
    }
}

