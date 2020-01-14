import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static List<List<Node>> adj;
    static List<Integer> dest;
    static int[] dists;
    static int n, m, t, s, g, h, a, b, d;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();
        while (T-- > 0) {
            n = io.nextInt();   m = io.nextInt();   t = io.nextInt();
            adj = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                adj.add(new ArrayList<>());
            }
            dest = new ArrayList<>();
            dists = new int[n + 1];
            s = io.nextInt();   g = io.nextInt();   h = io.nextInt();
            for (int i = 0; i < m; i++) {
                a = io.nextInt();   b = io.nextInt();   d = io.nextInt();
                adj.get(a).add(new Node(b, d));
                adj.get(b).add(new Node(a, d));
            }
            for (int i = 0; i < t; i++) {
                dest.add(io.nextInt());
            }
            Collections.sort(dest);

            for (Integer i : dest) {
                int d1 = dijkstra(s, i);
                if (d1 == INF)
                    continue;
                int d2 = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, i);
                int d3 = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, i);
                if (d1 == Math.min(d2, d3))
                    res.append(i).append(' ');
            }

            res.append('\n');
        }

        io.write(res);
    }

    private static int dijkstra(int s, int e) {
        Arrays.fill(dists, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        pq.add(new Node(s, dists[s] = 0));

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.idx;
            int curDist = tmp.dist;

            if (curDist > dists[cur])
                continue;

            for (Node n : adj.get(cur)) {
                int next = n.idx;
                int nextDist = n.dist;
                if (dists[next] > dists[cur] + nextDist)
                    pq.add(new Node(next, dists[next] = dists[cur] + nextDist));
            }
        }

        return dists[e];
    }
}

class Node {
    int idx, dist;

    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
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

    void write(char c) throws IOException {
        bw.write(c);
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