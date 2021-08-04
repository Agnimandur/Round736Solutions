//Written by Tlatoani (Runtime: 155ms)

fun main() {
    val n = readLine()!!.toInt()
    val amts = LongArray(4)
    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        amts[(2 * ((x / 2) % 2)) + ((y / 2) % 2)]++
    }
    var answer = 0L
    for (j in 0..3) {
        answer += (amts[j] * (amts[j] - 1L) * (amts[j] - 2L)) / 6L
        for (k in 0..3) {
            if (j != k) {
                answer += ((amts[j] * (amts[j] - 1)) / 2L) * amts[k]
            }
        }
    }
    println(answer)
}