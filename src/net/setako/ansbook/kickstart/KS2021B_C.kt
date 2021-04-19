import java.util.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

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
    val T = nLineInts()[0]
    repeat(T) { t ->
        val (N) = nLineLongs()

        fun isPrime(test: Int): Boolean {
            if (test <= 1) return false
            for (next in 2..sqrt(test.toDouble()).toInt()) {
                if (test % next == 0) return false
            }
            return true
        }

        fun findUpperPrime(from: Int): Int {
            while (true) {
                if (isPrime(from)) return from
                else return findUpperPrime(from+1)
            }
        }

        fun findLowerPrime(from: Int): Int {
            while (true) {
                if (isPrime(from)) return from
                else return findLowerPrime(from - 1)
            }
        }

        val mid = sqrt(N.toDouble())
        val upper = findUpperPrime(ceil(mid).toInt())
        val lower = findLowerPrime(floor(mid).toInt()).let { if (it == upper) findLowerPrime(it-1) else it }
        val result = when {
            lower.toLong() * upper <= N -> lower.toLong() * upper
            lower.toLong() * upper > N -> findLowerPrime(lower-1).toLong() * lower
            else -> TODO()
        }


        w("Case #${t + 1}: ")
        wl(result)
    }
}
