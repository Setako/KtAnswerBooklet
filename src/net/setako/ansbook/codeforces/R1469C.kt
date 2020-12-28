import java.util.*
import kotlin.math.max
import kotlin.math.min

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
private fun w(arr: Array<*>, separator: String = " ") = w(arr.joinToString(separator))
private fun wl() = output.println()
private fun wl(string: String) = output.println(string)
private fun wl(number: Number) = output.println(number.toString())
private fun wl(arr: Array<*>, separator: String = " ") = wl(arr.joinToString(separator))
//endregion

fun main() {
    val t = nLineInts()[0]

    repeat(t) {
        val k = nLineInts()[1]
        val hArr = nLineLongs()


        var positionLimitMax = hArr[0] + (k - 1)
        var positionLimitMin = hArr[0] - (k - 1)

        wl(run {
            hArr.drop(1).forEach { h ->
                val positionMax = h + (k - 1)
                val positionMin = h

                wl("$h , limit $positionLimitMax $positionLimitMin")

                if (positionMin > positionLimitMax || positionMax < positionLimitMin) return@run "No";

                positionLimitMax = min(positionLimitMax, positionMax) + (k - 1)
                positionLimitMin = max(positionLimitMin, positionMin) - (k - 1)
            }

            return@run "Yes"
        })
    }
}
