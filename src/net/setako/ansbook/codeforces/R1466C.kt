import java.util.*
import kotlin.collections.HashMap
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

class BiHashMap<A, B> private constructor(
    private val aMap: HashMap<A, B>, private val bMap: HashMap<B, A>
) : MutableMap<A, B> by aMap {
    constructor(abPairs: HashMap<A, B> = HashMap()) : this(
        HashMap(abPairs),
        abPairs.map { (a, b) -> b to a }.toMap(HashMap())
    )

    fun getByA(a: A) = aMap[a]
    fun getByB(b: B) = bMap[b]

    fun removeByA(a: A) = aMap.remove(a).let(bMap::remove)
    fun removeByB(b: B) = bMap.remove(b).let(aMap::remove)

    override fun put(a: A, b: B): B {
        aMap[a] = b
        bMap[b] = a
        return b
    }

    fun put(ab: Pair<A, B>) = put(ab.first, ab.second)
}
//endregion

fun main() {
    val t = nLineInts()[0]

    repeat(t) {
        wl(run {
            val chars = nLine().toCharArray().toList()

            val issues = HashSet<Int>();

            (0..chars.lastIndex).forEach { i ->
                if (i > 0 && chars[i] == chars[i - 1] && !issues.contains(i - 1)) {
                    issues.add(i)
                }
                if (i > 1 && chars[i] == chars[i - 2] && !issues.contains(i - 2)) {
                    issues.add(i)
                }
            }

            return@run issues.size
        })
    }
}
