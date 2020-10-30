import java.io.*
import java.util.*

const val INF = 100_000_000
lateinit var adj: Array<ArrayList<Pair<Int, Int>>>
lateinit var dists: IntArray

class Solution {
    fun solution(n: Int, road: Array<IntArray>, k: Int): Int {
        adj = Array(n + 1) { arrayListOf<Pair<Int, Int>>() }
        dists = IntArray(n + 1) { INF }.also { it[1] = 0 }
        for (i in road) {
            val (a, b, c) = i
            adj[a].add(Pair(b, c))
            adj[b].add(Pair(a, c))
        }
        dijkstra()
        return dists.filter { it <= k }.size
    }
    
    fun dijkstra() {
        val pq = PriorityQueue<Pair<Int, Int>> { p1, p2 -> p1.second - p2.second }
        pq.add(Pair(1, dists[1]))
        while (pq.isNotEmpty()) {
            val (cur, cd) = pq.poll()
            if (dists[cur] > cd)
                continue
            for (n in adj[cur]) {
                val next = n.first;
                val nd = n.second;
                if (dists[next] > dists[cur] + nd) {
                    dists[next] = dists[cur] + nd
                    pq.add(Pair(next, dists[next]))
                }
            }
        }
    }
}