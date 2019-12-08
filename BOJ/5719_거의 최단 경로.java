/**
 * https://www.acmicpc.net/problem/5719
 * 1. 다익스트라로 최단거리를 구하면서 경로를 저장한다. (모든 최단거리의 경로를 저장해야 한다.)
 * 2. 도착점에서 BFS를 하면서 경로를 지운다. (해당 경로까지의 거리를 -1로 바꿈)
 * 3. dists배열을 초기화하고 다시 다익스트라로 최단거리를 구한다.
 **/

import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int N, M, S, D, U, V, P;
    static List<List<Node>> adj;
    static List<List<Integer>> trace;
    static int[] dists;

    public static void main(String... args) throws IOException {
        StringBuilder res = new StringBuilder();

        while (run()) {
            adj = new ArrayList<>(N);
            trace = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
                trace.add(new ArrayList<>(N));
            }
            dists = new int[N];
            Arrays.fill(dists, INF);
            S = io.nextInt();
            D = io.nextInt();

            for (int i = 0; i < M; i++) {
                U = io.nextInt();
                V = io.nextInt();
                P = io.nextInt();
                adj.get(U).add(new Node(V, P));
            }

            dijkstra();
            BFS();
            Arrays.fill(dists, INF);
            dijkstra();
            res.append(dists[D] == INF ? -1 : dists[D]).append('\n');
        }

        io.write(res);
    }

    private static boolean run() {
        N = io.nextInt();
        M = io.nextInt();

        if (N == 0 && M == 0)
            return false;
        return true;
    }

    private static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(D);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int pre : trace.get(cur)) {
                Node preNode = null;

                for (Node n : adj.get(pre)) {
                    if (n.idx == cur)
                        preNode = n;
                }

                if (preNode.dist != -1 && dists[cur] == preNode.dist + dists[pre]) {
                    queue.add(pre);
                    preNode.dist = -1;
                }
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));
        dists[S] = 0;

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.idx;
            int curDist = tmp.dist;

            if (curDist > dists[cur])
                continue;

            for (Node n:
                    adj.get(cur)) {
                int next = n.idx;
                int nextDist = n.dist;

                if (nextDist != -1 && dists[next] >= dists[cur] + nextDist) {
                    dists[next] = dists[cur] + nextDist;
                    pq.add(new Node(next, dists[next]));
                    trace.get(next).add(cur);
                }
            }
        }
    }

}

class Node implements Comparable<Node>{
    int idx, dist;

    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }
}

class FastIO {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
        close();
    }

    void write(int i) throws IOException {
        bw.write(String.valueOf(i));
        close();
    }

    void write(long l) throws IOException {
        bw.write(String.valueOf(l));
        close();
    }

    void write(StringBuilder sb) throws IOException {
        bw.write(sb.toString().trim());
        close();
    }

    void write(String str) throws IOException {
        bw.write(str.trim());
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}