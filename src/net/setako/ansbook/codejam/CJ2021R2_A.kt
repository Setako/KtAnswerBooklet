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
private fun wl(vararg arr: Any, separator: String = " ") = wl(arr.joinToString(separator))

private fun booleanArrayToLong(booleanArray: Iterable<Boolean>) =
    booleanArray.fold(0L) { acc, b -> (acc shl 1) + if (b) 1 else 0 }

//endregion

fun main() {
    val (T, N) = nLineInts()
    repeat(T) { t ->
        var nextI = 1
        while (nextI != N) {
            wl("M", nextI, N)
            val swapWithI = nLineInts()[0].also { if (it == -1) throw IllegalArgumentException() }
            if (swapWithI != nextI) {
                wl("S $nextI $swapWithI")
                nLineInts()[0].also { if (it == -1) throw IllegalArgumentException() }
            }
            nextI++
        }
        wl("D")
        nLineInts()[0].also { if (it == -1) throw IllegalArgumentException() }
    }
}

