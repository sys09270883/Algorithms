import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int V, E, P, a, b, c, s, s1, s2;
    static List<List<Node>> adj;
    static int[] dists;

    public static void main(String... args) throws IOException {
        V = io.nextInt();
        E = io.nextInt();
        P = io.nextInt();
        adj = new ArrayList<>(V + 1);
        for (int i = 0; i < V + 1; i++) {
            adj.add(new ArrayList<>());
        }
        dists = new int[V + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < E; i++) {
            a = io.nextInt();
            b = io.nextInt();
            c = io.nextInt();
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        s = dijkstra(1, V);
        s1 = dijkstra(1, P);
        s2 = dijkstra(P, V);

        res.append(s == s1 + s2 ? "SAVE HIM" : "GOOD BYE");
        io.write(res);
    }

    private static int dijkstra(int s, int e) {
        Arrays.fill(dists, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
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

                if (dists[next] > dists[cur] + nextDist) {
                    dists[next] = dists[cur] + nextDist;
                    pq.add(new Node(next, dists[next]));
                }
            }
        }

        return dists[e];
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

    void write(char c) throws IOException {
        bw.write(c);
        close();
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

    void write(char[] str) throws IOException {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            bw.write(str[i]);
        }
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}