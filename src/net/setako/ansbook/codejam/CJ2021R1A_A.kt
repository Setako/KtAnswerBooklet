import java.math.BigInteger
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

//endregion

fun main() {
    val (T) = nLineInts()
    repeat(T) { t ->
        val N = nLineInts()[0]
        val XArr = nLineBigIntegers()

        var prev = XArr[0]
        var result = 0
        XArr.drop(1).forEach { next ->
            if (next <= prev) {
                if ("$next".length == "$prev".length) {
                    prev = "${next}0".toBigInteger()
                    result++
                } else {
                    val prefixLength = "$next".length
                    val suffixLength = "$prev".length - prefixLength
                    if ("$next" == "$prev".take(prefixLength) && "${
                            "$prev".drop(prefixLength).toBigInteger().add(BigInteger.ONE)
                        }".length <= suffixLength
                    ) {
                        val suffix = "${"$prev".drop(prefixLength).toBigInteger().add(BigInteger.ONE)}".padStart(suffixLength, '0')
                        prev = "$next${suffix}".toBigInteger()
                        result += suffixLength
                    } else {
                        if (next > "$prev".take(prefixLength).toBigInteger()) {
                            prev = "$next${(0 until suffixLength).map { 0 }.joinToString("")}".toBigInteger()
                            result += suffixLength
                        } else {
                            prev = "$next${(0..suffixLength).map { 0 }.joinToString("")}".toBigInteger()
                            result += suffixLength + 1
                        }
                    }
                }
            }else{
                prev = next
            }
        }

        wl("Case #${t + 1}: ${result}")
    }
}

