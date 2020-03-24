import java.util.*

var arr = Array(10) {IntArray(10)}
var curx = 0
var cury = 0

fun init() = with(Scanner(System.`in`)) {
    var str: String
    for (i in 1..9) {
        str = nextLine()
        for (j in 1..9) {
            arr[i][j] = str[j - 1] -'0'
        }
    }
}

fun isDone(): Boolean {
    for (i in 1..9) {
        for (j in 1..9) {
            if (arr[i][j] == 0) {
                curx = i
                cury = j
                return false
            }
        }
    }
    return true
}

fun check(x: Int, y: Int, n: Int): Boolean {
    return when {
        checkRow(x, n) && checkCol(y, n) && checkSquare(x, y, n) -> true
        else -> false
    }
}

fun checkSquare(x: Int, y: Int, n: Int): Boolean {
    val r = (Math.ceil(x.toDouble() / 3) - 1).toInt() * 3 + 1
    val c = (Math.ceil(y.toDouble() / 3) - 1).toInt() * 3 + 1
    for (i in 0..2) {
        for (j in 0..2) {
            if (arr[r + i][c + j] == n)
                return false
        }
    }
    return true
}

fun checkCol(y: Int, n: Int): Boolean {
    for (i in 1..9) {
        if (arr[i][y] == n)
            return false
    }
    return true
}

fun checkRow(x: Int, n: Int): Boolean {
    for (i in 1..9) {
        if (arr[x][i] == n)
            return false
    }
    return true
}

fun DFS(): Boolean {
    if (isDone())
        return true
    var x = curx
    var y = cury
    for (i in 1..9) {
        if (check(x, y, i)) {
            arr[x][y] = i
            if (DFS())
                return true
            arr[x][y] = 0
        }
    }
    return false
}

fun printArr() {
    var res = StringBuilder()
    for (i in 1..9) {
        for (j in 1..9) {
            res.append(arr[i][j])
        }
        res.append('\n')
    }
    print(res)
}

fun main() {
    init()
    DFS()
    printArr()
}