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
    val (T) = nLineInts()
    repeat(T) { t ->
        val (Ss, Es) = nLineWords()
        val S = Ss.map { it == '1' }.let { booleanArrayToLong(it) }
        val E = Es.map { it == '1' }.let { booleanArrayToLong(it) }

        val result = if (S == E) 0 else run {
            var values = setOf(S)
            var count = 1
            while (true) {
                values = values.flatMap { s ->
                    if (count > 20) return@run -1
                    val not = s.toULong().toString(2).map { it == '0' }.let { booleanArrayToLong(it) }
                    val double = s * 2
                    if (not == E || double == E) return@run count
                    setOf(not, double)
                }.toSet()
                count++
            }
        }

        wl(
            "Case #${t + 1}: ${if(result == -1) "IMPOSSIBLE" else result}"
        )
    }
}

