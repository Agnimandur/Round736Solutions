//Written by Tlatoani (Runtime: 4289ms)

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
 
fun main() {
    val n = readLine()!!.toInt()
    val posts = Array(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        Pair(x, y)
    }
    val gcds = Array(n) { j -> IntArray(n) { k -> if (j < k) gcd(abs(posts[j].first - posts[k].first), abs(posts[j].second - posts[k].second)) else 0 } }
    var single = 0L
    var triple = 0L
    for (r in 0 until n) {
        val (rx, ry) = posts[r]
        val amts = Array(4) { Array(4) { LongArray(4) } }
        for (j in 0 until n) {
            if (j != r) {
                val (x, y) = posts[j]
                val a = gcds[min(r, j)][max(r, j)] % 4
                val b = (((x - rx) % 4) + 4) % 4
                val c = (((y - ry) % 4) + 4) % 4
                amts[a][b][c]++
            }
        }
        for (a in 0 until 4) for (b in  0 until 4) for (c in 0 until 4) {
            for (d in 0 until 4) for (e in 0 until 4) for (f in 0 until 4) {
                if (a % 2 == d % 2) {
                    val area = (b * f) - (c * e)
                    if (area % 2 == 0) {
                        var residue = 2 + area - a - d - gcd(abs(b - e), abs(c - f))
                        if (((residue % 4) + 4) % 4 == 2) {
                            val amt = if (a == d && b == e && c == f) amts[a][b][c] * (amts[a][b][c] - 1L) else amts[a][b][c] * amts[d][e][f]
                            if (a % 2 == 0) {
                                triple += amt
                            } else {
                                single += amt
                            }
                        }
                    }
                }
            }
        }
    }
    val answer = (single + (triple / 3L)) / 2L
    println(answer)
}
 
fun gcd(x: Int, y: Int): Int {
    var x = x
    var y = y
    while (y != 0) {
        x %= y
        val z = x
        x = y
        y = z
    }
    return x
}