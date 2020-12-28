package net.setako.ansbook

import java.util.*
import kotlin.collections.HashMap

//region Utils Declaration
private val output = System.out
private val input = Scanner(System.`in`)

private fun nLong() = input.nextLong()
private fun nDouble() = input.nextDouble()
private fun nWord() = input.next()
private fun nBigInteger() = input.nextBigInteger()

private fun nLine() = input.nextLine()
private fun nLineWords() = nLine().split(" ")
private fun nLineLongs() = nLineWords().map { it.toLong() }
private fun nLineDoubles() = nLineWords().map { it.toDouble() }
private fun nLineBigIntegers() = nLineWords().map { it.toBigInteger() }

private fun w(string: String) = output.print(string)
private fun w(number: Number) = output.print(number.toString())
private fun w(arr: Array<*>, separator: String = " ") = w(arr.joinToString(separator))

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

}
