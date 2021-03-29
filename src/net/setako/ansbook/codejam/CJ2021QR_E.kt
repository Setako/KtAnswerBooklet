import java.util.*
import kotlin.math.abs
import kotlin.math.pow

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

fun sigmoid(x: Double) = 1 / (1 + Math.E.pow(-x))

data class Player(
    val index: Int,
    val correctPercentage: Double,
    var records: List<Boolean>,
    var weird: Int = 0,
) {
}

data class Question(
    val index: Int,
    val correctPercentage: Double,
)

fun main() {
    val (T) = nLineInts()
    val P = nLineInts()[0]
    repeat(T) { t ->
        val playerRecords = (0..99).map { nLine().map { if (it == '1') 1 else 0 }.toList() }
        val players = playerRecords
            .mapIndexed { index, records ->
                Player(
                    index,
                    records.sum().toDouble() / 10000,
                    records = records.map { it == 1 })
            }
            .sortedBy { it.correctPercentage }

        val questions = (0..9999).map { playerRecords.map { records -> records[it] } }
            .mapIndexed { index, records -> Question(index, records.sum().toDouble() / 100) }


        players.forEachIndexed { i, player ->
            if (player.correctPercentage > 0.3) {
                val references = when (i) {
                    players.size - 1 -> listOf(players[i - 2], players[i - 1])
                    else -> listOf(players[i - 1], players[i + 1])
                }

                questions.forEach { question ->
                    if (references.map { it.records[question.index] }.distinct()
                            .count() == 1 && player.records[question.index] != references[0].records[question.index]
                    ) {
                        player.weird++
                    }
                }
            }
        }

        players.sortedByDescending { it.weird }.forEach {
            wl("${it.index} ${it.weird} ${it.correctPercentage}");
        }
        wl("Case #${t + 1}: ${players.sortedByDescending { it.weird }.first().index+1}")
    }
}

