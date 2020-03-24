import java.util.*

class Node(var idx: Int, var cnt: Int){}

const val MAX = 1e5

fun range(n: Int): Boolean {
    if (n < 0 || n > MAX)
        return false
    return true
}

fun BFS(s: Int, e: Int): Int {
    var vis = Array((MAX + 1).toInt()) { _ -> Integer.MAX_VALUE }
    val q: Queue<Node> = LinkedList<Node>()
    q.add(Node(s, 0))
    vis[s] = 0
    while (!q.isEmpty()) {
        val tmp: Node = q.poll()
        val cur = tmp.idx
        val cnt = tmp.cnt
        var next = cur + 1
        if (range(next)) {
            if (vis[next] > cnt + 1) {
                vis[next] = cnt + 1
                q.add(Node(next, cnt + 1))
            }
        }
        next = cur - 1
        if (range(next)) {
            if (vis[next] > cnt + 1) {
                vis[next] = cnt + 1
                q.add(Node(next, cnt + 1))
            }
        }
        next = cur shl 1
        if (range(next)) {
            if (vis[next] > cnt) {
                vis[next] = cnt
                q.add(Node(next, cnt))
            }
        }
    }
    return vis[e]
}

fun main() = with(Scanner(System.`in`)){
    val N = nextInt()
    val K = nextInt()
    println(BFS(N, K))
}