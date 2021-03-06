import java.util.*
import kotlin.collections.ArrayList
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
    val t = nLineInts()[0]

    repeat(t) {
        nLine()
        val vertiesWeights = nLineLongs()

        val nonZeroDegreeVerties = HashSet<Int>()
        val extraWeights = ArrayList<Long>();

        repeat(vertiesWeights.size - 1) {
            repeat(2) {
                nInt().let { vertex ->
                    if (nonZeroDegreeVerties.contains(vertex - 1)) {
                        extraWeights.add(vertiesWeights[vertex - 1])
                    } else {
                        nonZeroDegreeVerties.add(vertex - 1)
                    }
                }
            }
        }

        val answer = ArrayList<Long>()
        answer.add(vertiesWeights.sum())

        extraWeights.sort()
        extraWeights.reverse()
        repeat(vertiesWeights.size - 2) {
            answer.add(answer.last() + extraWeights[it])
        }

        wl(answer)
        nLine()
    }
}
