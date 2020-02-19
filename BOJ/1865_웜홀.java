import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = 987654321;
    static int N, M, W;
    static Edge[] edges;
    static int[] dists;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();
        while (T-- > 0) {
            N = io.nextInt();   M = io.nextInt();   W = io.nextInt();
            dists = new int[N + 1];
            Arrays.fill(dists, INF);
            edges = new Edge[2 * M + W];
            for (int i = 0; i < 2 * M; i += 2) {
                int a = io.nextInt(), b = io.nextInt(), c = io.nextInt();
                edges[i] = new Edge(a, b, c);
                edges[i + 1] = new Edge(b, a, c);
            }
            for (int i = 2 * M; i < 2 * M + W; i++) {
                edges[i] = new Edge(io.nextInt(), io.nextInt(), -io.nextInt());
            }
            res.append(bellman() ? "YES\n" : "NO\n");
        }
        io.write(res);
    }

    private static boolean bellman() {
        boolean negCyc = false;
        dists[1] = 0;
        for (int i = 1; i < N + 1; i++) {
            for (Edge e : edges) {
                int cur = e.n1, next = e.n2, nd = e.d;
                if (dists[cur] == INF)
                    continue;
                if (dists[next] > dists[cur] + nd) {
                    dists[next] = dists[cur] + nd;
                    if (i == N)
                        negCyc = true;
                }
            }
        }
        return negCyc;
    }

}

class Edge {
    int n1, n2, d;

    public Edge(int n1, int n2, int d) {
        this.n1 = n1;
        this.n2 = n2;
        this.d = d;
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

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }

        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
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