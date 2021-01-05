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
    class KdNode<T>(
        val data: T,
        val isVertical: Boolean,
        var left: KdNode<T>? = null,
        var right: KdNode<T>? = null
    )

    data class Friend(
        val h: Int,
        val w: Int,
        val index: Int,
    )

    val t = nLineInts()[0]

    repeat(t) {
        val n = nInt()
        (0 until n).map { n }
        val fds = (0 until n).map { i -> Friend(nInt(), nInt(), i) }

        fun createNode(isVertical: Boolean, arr: Collection<Friend>): KdNode<Friend>? {
            if (arr.isEmpty()) return null
            val sorted = if (isVertical) arr.sortedBy { it.w } else arr.sortedBy { it.h }
            val middle = arr.size / 2
            return KdNode(
                sorted[middle],
                isVertical,
                createNode(!isVertical, sorted.take(middle)),
                createNode(!isVertical, sorted.takeLast(middle - (if (sorted.size % 2 == 0) 1 else 0))),
            )
        }

        val root = createNode(true, fds)!!
        val rootR = createNode(true, fds.map { Friend(it.w, it.h, it.index) })!!

        fun search(kdNode: KdNode<Friend>, w: Int, h: Int): Friend? {
            if (kdNode.data.h < h && kdNode.data.w < w) return kdNode.data
            return if (kdNode.isVertical) {
                if (kdNode.data.w < w) {
                    kdNode.left?.let { search(it, w, h) } ?: kdNode.right?.let { search(it, w, h) }
                } else {
                    kdNode.left?.let { search(it, w, h) }
                }
            } else {
                if (kdNode.data.h < h) {
                    kdNode.left?.let { search(it, w, h) } ?: kdNode.right?.let { search(it, w, h) }
                } else {
                    kdNode.left?.let { search(it, w, h) }
                }
            }
        }

        wl(fds.map { fd -> (search(root, fd.w, fd.h) ?: search(rootR, fd.w, fd.h))?.index?.let { it + 1 } ?: -1 })
    }
}
