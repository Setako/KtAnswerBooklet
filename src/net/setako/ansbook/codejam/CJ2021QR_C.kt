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
    val T = nLineInts()[0]
    repeat(T) { t ->
        var (N, C) = nLineInts()

        val steps = arrayListOf<Pair<Int, Int>>()

        run {
            (0 until N - 1).forEach { i ->
                val maxValue = N - i
                val move = (C - (N - i - 2)).coerceAtMost(maxValue)
                if (move < 1) return@run "IMPOSSIBLE"
                C -= move

                steps.add(i to (move + i - 1))
            }

            if (C > 0) return@run "IMPOSSIBLE"

            val result = (1..N).toMutableList()
            steps.reversed().forEach { (a, b) ->
                result.take(b+1).drop(a).reversed().forEachIndexed { index, v ->
                    result[a+index] = v
                }
            }
            return@run result.joinToString(" ")
        }.let {
            wl("Case #${t + 1}: ${it}")
        }

    }
}

