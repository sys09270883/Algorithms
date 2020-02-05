import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = (int)1e9;
    static int N, M, x, y, w;
    static long total, res;
    static List<Edge> edges;
    static int[] parent;
    static long[] size;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        parent = new int[N + 1];
        size = new long[N + 1];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(size, 1);
        edges = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            x = io.nextInt();   y = io.nextInt();   w = io.nextInt();
            edges.add(new Edge(x ,y, w));
            total += w;
        }
        Collections.sort(edges, (e1, e2) -> e2.w - e1.w);

        for (Edge e : edges) {
            int n1 = find(e.n1);
            int n2 = find(e.n2);
            int w = e.w;

            if (find(n1) != find(n2)) {
                res += size[n1] * size[n2] % INF * total;
                res %= INF;
                union(n1, n2);
            }

            total -= w;
        }

        io.write(res);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        parent[y] = x;
        size[x] += size[y];
        size[y] = 1;
    }

}

class Edge {
    int n1, n2, w;

    public Edge(int n1, int n2, int w) {
        this.n1 = n1;
        this.n2 = n2;
        this.w = w;
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