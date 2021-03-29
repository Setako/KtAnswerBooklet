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
    val T = readLine()!!.toInt()
    repeat(T) { t ->
        val (N) = nLineInts()
        val L = nLineInts().toMutableList()

        var result = 0
        repeat(N-1) { startIndex ->
            val minIndex = L.mapIndexed { index, i -> i to index }.drop(startIndex).minByOrNull { it.first }!!.second
            if (minIndex != startIndex) {
                result += minIndex - startIndex +1
                L.take(minIndex + 1).drop(startIndex).reversed().forEachIndexed { i, v ->
                    L[i + startIndex] = v
                }
            }else{
                result++
            }
        }

        wl("Case #${t + 1}: ${result}")
    }
}

