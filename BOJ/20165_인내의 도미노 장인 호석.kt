import java.util.*
import java.io.*
import kotlin.math.*

var N = 0
var M = 0
var R = 0
lateinit var arr: Array<IntArray>
lateinit var fell: Array<BooleanArray>
val dx = intArrayOf(-1, 0, 1, 0)
val dy = intArrayOf(0, -1, 0, 1)
val dir = hashMapOf("N" to 0, "W" to 1, "S" to 2, "E" to 3)
val sb = StringBuilder()
var score = 0

fun main() = with(FastIO()) {
    N = nextInt()
    M = nextInt()
    R = nextInt()
    arr = Array(N + 1) { IntArray(M + 1) }
    fell = Array(N + 1) { BooleanArray(M + 1) { false } }
    for (i in 1..N) {
        for (j in 1..M) {
            arr[i][j] = nextInt()
        }
    }
    repeat(R) {
        var X = nextInt()
        var Y = nextInt()
        var D = next()
        go(X, Y, dir[D]!!)
        X = nextInt()
        Y = nextInt()
        fell[X][Y] = false
    }

    sb.append(score).append('\n')
    for (i in 1..N) {
        for (j in 1..M) {
            sb.append(if (fell[i][j]) "F" else "S").append(' ')
        }
        sb.append('\n')
    }

    write(sb)
}

fun go(x: Int, y: Int, d: Int) {
    if (fell[x][y])
        return
    fell[x][y] = true
    score++
    val v = arr[x][y]
    var nx = x
    var ny = y
    for (i in 0 until v - 1) {
        nx += dx[d]
        ny += dy[d]
        if (nx < 1 || ny < 1 || nx > N || ny > M)
            break
        go(nx, ny, d)
    }
}

class FastIO {
    fun next(): String {
        while (st == null || !st!!.hasMoreTokens()) {
            st = StringTokenizer(br.readLine())
        }
        return st!!.nextToken()
    }

    fun nextInt() = next().toInt()

    fun nextLong() = next().toLong()

    fun nextDouble() = next().toDouble()

    fun nextLine() = br.readLine()

    fun write(d: Double) {
        bw.write(d.toString())
        close()
    }

    fun write(c: Char) {
        bw.write(c.toInt())
        close()
    }

    fun write(i: Int) {
        bw.write(i.toString())
        close()
    }

    fun write(l: Long) {
        bw.append(l.toString())
        close()
    }

    fun write(sb: StringBuilder) {
        bw.write(sb.toString().trim())
        close()
    }

    fun write(str: String) {
        bw.write(str)
        close()
    }

    fun close() {
        bw.close()
        br.close()
    }

    companion object {
        val br: BufferedReader by lazy { BufferedReader(InputStreamReader(System.`in`)) }
        val bw: BufferedWriter by lazy { BufferedWriter(OutputStreamWriter(System.out)) }
        var st: StringTokenizer? = null
    }

}