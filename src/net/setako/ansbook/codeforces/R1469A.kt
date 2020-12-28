import java.util.*
import kotlin.math.abs

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

        val l = nLine()

        fun test(line: String, reversed: Boolean): Boolean {
            var a = 0
            var b = 0
            line.toCharArray().forEach {
                if(reversed){
                    when (it) {
                        ')' -> a += 1
                        '?' -> b++
                        '(' -> a -= 1
                    }
                }else{
                    when (it) {
                        '(' -> a += 1
                        '?' -> b++
                        ')' -> a -= 1
                    }
                }
                if (b < -a) return false
            }

            return !(abs(a) > b || (a + b) % 2 != 0)
        }

        if (test(l, false) && test(l.reversed(), true)) wl("Yes") else wl("No")

    }
}
