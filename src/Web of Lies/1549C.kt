//Written by Tlatoani (Runtime: 467ms)

import kotlin.math.min
 
fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val above = IntArray(n + 1)
    above[0] = -1
    repeat(m) {
        above[readLine()!!.split(" ").map { it.toInt() }.min()!!]++
    }
    var answer = above.count { it == 0 }
    val out = StringBuilder()
    repeat(readLine()!!.toInt()) {
        val query = readLine()!!.split(" ").map { it.toInt() }
        when (query[0]) {
            1 -> {
                val a = min(query[1], query[2])
                if (above[a] == 0) {
                    answer--
                }
                above[a]++
            }
            2 -> {
                val a = min(query[1], query[2])
                above[a]--
                if (above[a] == 0) {
                    answer++
                }
            }
            3 -> out.appendln(answer)
        }
    }
    print(out)
}