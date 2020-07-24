import java.io.*
import java.util.*

val io = FastIO()
var n = 0
var k = 0
val res = StringBuilder()

fun main() {
    n = io.nextInt()
    k = io.nextInt()
    if (n == 2) {
        res.append("YES\n")
        repeat(5) {
            res.append("swap 1 2\n")
        }
    }
    else if (n == 3)
        res.append("NO\n")
    else if (k == 1 || k == n - 1) {
        if (k == 1) {
            res.append("YES\n").append("reverse 2 $n\n").append("swap 1 $n\n")
                .append("reverse 2 ${n - 1}\n").append("swap 1 2\n").append("swap 1 2\n")
        }
        else {
            res.append("YES\n").append("reverse 1 ${n - 1}\n").append("swap 1 $n\n")
                .append("reverse 2 ${n - 1}\n").append("swap 1 2\n").append("swap 1 2\n")
        }
    }
    else {
        res.append("YES\n").append("reverse 1 $k\n").append("reverse ${k + 1} $n\n")
            .append("reverse 1 $n\n").append("swap 1 2\n").append("swap 1 2\n")
    }
    io.write(res)
}

class FastIO {
    @Throws(IOException::class)
    fun next(): String {
        while (st == null || !st!!.hasMoreTokens()) {
            st = StringTokenizer(br.readLine())
        }
        return st!!.nextToken()
    }

    @Throws(IOException::class)
    fun nextInt(): Int {
        return next().toInt()
    }

    @Throws(IOException::class)
    fun nextLong(): Long {
        return next().toLong()
    }

    @Throws(IOException::class)
    fun nextDouble(): Double {
        return next().toDouble()
    }

    @Throws(IOException::class)
    fun nextLine(): String {
        return br.readLine()
    }

    @Throws(IOException::class)
    fun write(d: Double) {
        bw.write(d.toString())
        close()
    }

    @Throws(IOException::class)
    fun write(c: Char) {
        bw.write(c.toInt())
        close()
    }

    @Throws(IOException::class)
    fun write(i: Int) {
        bw.write(i.toString())
        close()
    }

    @Throws(IOException::class)
    fun write(l: Long) {
        bw.write(l.toString())
        close()
    }

    @Throws(IOException::class)
    fun write(sb: StringBuilder) {
        bw.write(sb.toString().trim { it <= ' ' })
        close()
    }

    @Throws(IOException::class)
    fun write(str: String) {
        bw.write(str.trim { it <= ' ' })
        close()
    }

    @Throws(IOException::class)
    fun close() {
        bw.close()
        br.close()
    }

    companion object {
        lateinit var br: BufferedReader
        lateinit var bw: BufferedWriter
        var st: StringTokenizer? = null
    }

    init {
        br = BufferedReader(InputStreamReader(System.`in`))
        bw = BufferedWriter(OutputStreamWriter(System.out))
    }
}