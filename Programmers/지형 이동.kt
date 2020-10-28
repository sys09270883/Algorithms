import java.io.*
import java.util.*

data class Edge(var a: Int, var b: Int, var c: Int)

var N = 0
var M = 0
var H = 0
var res = 0
lateinit var par: IntArray
val edges = arrayListOf<Edge>()
lateinit var arr: Array<IntArray>
lateinit var vis: Array<IntArray>
val dx = arrayOf(-1, 0, 1, 0)
val dy = arrayOf(0, -1, 0, 1)

tailrec fun find(x: Int): Int {
    par[x] = if (x == par[x]) x else find(par[x])
    return par[x]
}

fun union(x: Int, y: Int) {
    val fx = find(x)
    val fy = find(y)
    if (fx != fy)
        par[fx] = fy
}

fun bfs(a: Int, b: Int, id: Int) {
    val q = LinkedList<Pair<Int, Int>>()
    q.push(Pair(a, b))
    vis[a][b] = id
    while (q.isNotEmpty()) {
        val (x, y) = q.poll()
        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue
            val diff = Math.abs(arr[x][y] - arr[nx][ny])
            if (vis[nx][ny] > 0 || diff > H)
                continue
            vis[nx][ny] = id
            q.push(Pair(nx, ny))
        }
    }
}

class Solution {
    fun solution(land: Array<IntArray>, height: Int): Int {
        arr = land
        H = height
        N = land.size
        M = land[0].size
        vis = Array(N) { IntArray(M) { 0 } }
        var id = 0
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (vis[i][j] == 0)
                    bfs(i, j, ++id)
            }
        }
        for (i in 0 until N) {
            for (j in 0 until M) {
                for (k in 0..3) {
                    val nx = i + dx[k]
                    val ny = j + dy[k]
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || vis[nx][ny] == vis[i][j])
                        continue
                    edges.add(Edge(vis[i][j], vis[nx][ny], Math.abs(arr[i][j] - arr[nx][ny])))
                }
            }
        }
        par = IntArray(id + 1) { i -> i }
        edges.sortBy { e -> e.c }
        for (e in edges) {
            val fa = find(e.a)
            val fb = find(e.b)
            if (fa != fb) {
                union(fa, fb)
                res += e.c
            }
        }
        return res
    }
}