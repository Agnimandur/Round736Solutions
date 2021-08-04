//Written by Tlatoani (Runtime: 1310ms)

import java.util.*
import kotlin.math.min
 
fun main() {
    val (n, m, x) = readLine()!!.split(" ").map { it.toInt() }
    val ay = readLine()!!.split(" ").map { it.toInt() }
    val by = readLine()!!.split(" ").map { it.toInt() }
    val rowIntervals = findIntervals(ay)
    val columnIntervals = findIntervals(by)
    var answer = 0L
    val bit = BinaryIndexTree(1, 200000)
    val pqIn = PriorityQueue<Interval>(compareByDescending { it.minEndpoint })
    val pqOut = PriorityQueue<Interval>(compareByDescending { it.maxInside })
    pqIn.addAll(columnIntervals)
    for ((minInside, maxInside, minEndpoint) in rowIntervals.sortedBy { it.minInside }) {
        while (pqIn.isNotEmpty() && pqIn.peek().minEndpoint + minInside > x) {
            val interval = pqIn.remove()
            bit.update(interval.minInside, 1L)
            pqOut.add(interval)
        }
        while (pqOut.isNotEmpty() && pqOut.peek().maxInside + minInside > x) {
            val interval = pqOut.remove()
            bit.update(interval.minInside, -1L)
        }
        answer += bit[x - minEndpoint + 1, x - maxInside]
    }
    println(answer)
}
 
data class Interval(val minInside: Int, val maxInside: Int, val minEndpoint: Int)
 
const val BILLION = 1000000000
 
fun findIntervals(actualXS: List<Int>): List<Interval> {
    val xs = listOf(BILLION) + actualXS + listOf(BILLION)
    val segTree = SegmentTree(0, xs.lastIndex)
    for ((j, x) in xs.withIndex()) {
        segTree[j] = x
    }
    val res = mutableListOf<Interval>()
    val stack = Stack<Int>()
    for ((j, x) in xs.withIndex()) {
        if (stack.isNotEmpty() && xs[stack.peek()] <= x) {
            var last = xs[stack.pop()]
            while (stack.isNotEmpty() && xs[stack.peek()] <= x) {
                res.add(Interval(segTree[stack.peek() + 1, j - 1], last, xs[stack.peek()]))
                last = xs[stack.pop()]
            }
            if (stack.isNotEmpty()) {
                res.add(Interval(segTree[stack.peek() + 1, j - 1], last, x))
            }
        }
        stack.push(j)
    }
    return res
}
 
class BinaryIndexTree(val treeFrom: Int, treeTo: Int) {
    val value = LongArray(treeTo - treeFrom + 2)
 
    fun update(index: Int, delta: Long) {
        var i = index + 1 - treeFrom
        while (i < value.size) {
            value[i] += delta
            i += i and -i
        }
    }
 
    fun query(to: Int): Long {
        var res = 0L
        var i = to + 1 - treeFrom
        while (i > 0) {
            res += value[i]
            i -= i and -i
        }
        return res
    }
 
    operator fun get(from: Int, to: Int) = if (to < from) 0L else query(to) - query(from - 1)
}
 
class SegmentTree(val treeFrom: Int, treeTo: Int) {
    val value: IntArray
    val length: Int
 
    init {
        var e = 0
        while (1 shl e < treeTo - treeFrom + 1) {
            e++
        }
        value = IntArray(1 shl (e + 1))
        length = 1 shl e
    }
 
    operator fun set(index: Int, delta: Int) {
        var node = index - treeFrom + length
        value[node] = delta
        node = node shr 1
        while (node > 0) {
            value[node] = min(value[node shl 1], value[(node shl 1) + 1])
            node = node shr 1
        }
    }
 
    operator fun get(index: Int) = value[index - treeFrom + length]
 
    operator fun get(fromIndex: Int, toIndex: Int): Int {
        if (toIndex < fromIndex) {
            return Int.MAX_VALUE
        }
        var from = fromIndex + length - treeFrom
        var to = toIndex + length - treeFrom + 1
        var res = Int.MAX_VALUE
        while (from + (from and -from) <= to) {
            res = min(res, value[from / (from and -from)])
            from += from and -from
        }
        while (to - (to and -to) >= from) {
            res = min(res, value[(to - (to and -to)) / (to and -to)])
            to -= to and -to
        }
        return res
    }
}