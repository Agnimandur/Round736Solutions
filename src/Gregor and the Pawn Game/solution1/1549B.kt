//Written by Tlatoani (Runtime: 265ms)

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val enemies = readLine()!!.toCharArray()
        val here = readLine()!!.toCharArray()
        var answer = 0
        for (k in 0 until n) {
            if (here[k] == '1') {
                if (k > 0 && enemies[k - 1] == '1') {
                    enemies[k - 1] = '2'
                    answer++
                } else if (enemies[k] == '0') {
                    enemies[k] = '2'
                    answer++
                } else if (k < n - 1 && enemies[k + 1] == '1') {
                    enemies[k + 1] = '2'
                    answer++
                }
            }
        }
        println(answer)
    }
}