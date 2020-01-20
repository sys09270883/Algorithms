import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[][] arr;
    static List<Edge> edges;
    static int[] parent;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N][N];
        edges = new ArrayList<>();
        parent = new int[N];
        Arrays.setAll(parent, i -> i);
        long total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = io.nextInt();
                if (arr[i][j] != 0)
                    edges.add(new Edge(i, j, arr[i][j]));
            }
        }

        Collections.sort(edges, (e1, e2) -> e1.w - e2.w);

        for (Edge e : edges) {
            int n1 = e.n1, n2 = e.n2, w = e.w;
            if (find(n1) != find(n2)) {
                union(n1, n2);
                total += w;
            }
        }

        io.write(total);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = parent[x];
        y = parent[y];
        if (x != y)
            parent[y] = x;
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