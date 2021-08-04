//Written by Tlatoani (Runtime: 686ms)

import java.util.*
import kotlin.math.abs
import kotlin.math.max
 
fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val ay = listOf(0L) + readLine()!!.split(" ").map { it.toLong() }
        var answer = 1
        val deque = ArrayDeque<Pair<Int, Long>>()
        deque.push(Pair(1, 1L))
        for (k in 2..n) {
            val x = abs(ay[k] - ay[k - 1])
            deque.push(Pair(k, 0))
            fun recalc() {
                if (deque.isNotEmpty()) {
                    val (j, z) = deque.pop()
                    recalc()
                    val y = gcd(x, z)
                    if (deque.isNotEmpty() && y == deque.peek().second) {
                        deque.pop()
                    }
                    deque.push(Pair(j, y))
                }
            }
            recalc()
            answer = max(answer, k - deque.last.first + 1)
        }
        println(answer)
    }
}
 
fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)