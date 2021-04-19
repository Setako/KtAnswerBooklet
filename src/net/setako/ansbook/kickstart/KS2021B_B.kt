import java.util.*
import kotlin.collections.HashMap

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
        val (N) = nLineInts()
        val AArr = nLineInts()

        data class AAKey(val fromIndex: Int, val diff: Int, val replaced: Boolean, val next: Int)

        var arithmeticArrCache = HashMap<AAKey, Int>()
        fun findArithmeticArr(fromIndex: Int, diff: Int, replaced: Boolean, next: Int = AArr[fromIndex] + diff): Int {
            return arithmeticArrCache.getOrPut(AAKey(fromIndex, diff, replaced, next)) {
                if (AArr.size <= fromIndex + 1) return 1
                when {
                    AArr[fromIndex + 1] == next -> findArithmeticArr(fromIndex + 1, diff, replaced, next + diff) + 1;
                    replaced -> 1
                    else -> findArithmeticArr(fromIndex + 1, diff, true, next + diff) + 1
                }
            }
        }

        var result = 1
        AArr.forEachIndexed { index, i ->
            if (AArr.size > index + 1) result =
                findArithmeticArr(index, AArr[index + 1] - i, false).coerceAtLeast(result)
            if (AArr.size > index + 2) {
                if ((AArr[index + 2] - i) % 2 == 0) result =
                    findArithmeticArr(index, (AArr[index + 2] - i) / 2, false).coerceAtLeast(result)
                result =
                    findArithmeticArr(index, (AArr[index + 2] - AArr[index + 1]), true, AArr[index + 1])
                        .coerceAtLeast(result)
            }
        }

        w("Case #${t + 1}: ")
        wl(result)
    }
}
