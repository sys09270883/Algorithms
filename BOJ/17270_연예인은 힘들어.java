import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int V, M, J, S, a, b, c;
    static List<List<Node>> adj;

    public static void main(String... args) throws IOException {
        V = io.nextInt();   M = io.nextInt();
        adj = new ArrayList<>(V + 1);
        for (int i = 0; i < V + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            a = io.nextInt();   b = io.nextInt();   c = io.nextInt();
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }
        J = io.nextInt();   S = io.nextInt();

        int[] distJ = dijkstra(J);
        int[] distS = dijkstra(S);
        if (distJ[S] != INF) {
            int dest = -1, minDist = INF, tmp = INF;
            for (int i = 1; i < V + 1; i++) {
                if (i == J || i == S || distJ[i] == INF || distS[i] == INF)
                    continue;
                minDist = Math.min(minDist, distJ[i] + distS[i]);
            }

            for (int i = 1; i < V + 1; i++) {
                if (i == J || i == S || distJ[i] == INF || distS[i] == INF)
                    continue;
                if (minDist != distJ[i] + distS[i] || distJ[i] > distS[i])
                    continue;
                if (tmp > distJ[i]) {
                    tmp = distJ[i];
                    dest = i;
                }
            }
            io.write(dest);
        }
        else
            io.write(-1);
    }

    private static int[] dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.dist - n2.dist);
        int[] dists = new int[V + 1];
        Arrays.fill(dists, INF);
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
                    pq.add(new Node(next, dists[next] = dists[cur] + nextDist));
                }
            }
        }

        return dists;
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