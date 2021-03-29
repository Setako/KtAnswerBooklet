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
    val (T, N, Q) = nLineInts()
    repeat(T) { t ->
        val unresolved = (1..N).shuffled().toMutableSet()

        fun ask(a: Int, b: Int, c: Int): Int {
            wl(a, b, c)
            return nLineInts()[0]
                .also { if (it == -1) throw IllegalStateException() }
        }

        val resolved = LinkedList<Int>()

        fun pop() = unresolved.random().also { unresolved.remove(it) }

        val f = listOf(pop(), pop(), pop()).toMutableList()
        val fm = ask(f[0], f[1], f[2])
        f.remove(fm)
        resolved.add(f[0])
        resolved.add(fm)
        resolved.add(f[1])

        fun resolve(_fromIndex: Int, _toIndex: Int, next: Int) {
            assert(_toIndex - _fromIndex >= 0)
            val fromIndex = if (_toIndex - _fromIndex == 0 && _fromIndex != 0) _fromIndex - 1 else _fromIndex
            val toIndex = if (_toIndex - fromIndex == 0 && _toIndex != resolved.size - 1) _toIndex + 1 else _toIndex

            val midLeftIndex = ((toIndex - fromIndex) / 2) + fromIndex
            val midRightIndex = (midLeftIndex + 1)
            val midLeft = resolved[midLeftIndex]
            val midRight = resolved[midRightIndex]
            val newMid = ask(midLeft, midRight, next)

            when (newMid) {
                midLeft -> {
                    val noLeft = midLeftIndex == fromIndex
                    if (noLeft) resolved.add(fromIndex, next)
                    else resolve(fromIndex, midLeftIndex - 1, next)
                }
                midRight -> {
                    val noRight = midRightIndex == toIndex
                    if (noRight) resolved.add(toIndex + 1, next)
                    else resolve(midRightIndex + 1, toIndex, next)
                }
                next -> {
                    resolved.add(midLeftIndex + 1, next)
                }
            }
        }

        while (unresolved.isNotEmpty()){
//            wl("Current: ", resolved)
            resolve(0, resolved.size - 1, pop())
        }

        wl(resolved)
        nLineInts()[0]
    }
}

