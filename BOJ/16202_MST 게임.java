import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, K, score;
    static int[] par;
    static LinkedList<Edge> edges;
    static Set<Integer> hs;
    static boolean flag;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();   K = io.nextInt();
        par = new int[N + 1];
        edges = new LinkedList<>();
        for (int i = 1; i < M + 1; i++) {
            edges.add(new Edge(io.nextInt(), io.nextInt(), i));
        }
        hs = new HashSet<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < K; i++) {
            Arrays.fill(par, -1);
            hs.clear();
            score = 0;
            for (Edge e : edges) {
                if (find(e.v1) != find(e.v2)) {
                    union(e.v1, e.v2);
                    score += e.c;
                }
            }
            flag = true;
            for (int j = 1; j < N + 1; j++) {
                if (par[j] == -1)
                    flag = false;
                else
                    hs.add(find(j));
            }
            if (hs.size() >= 2)
                flag = false;
            res.append(flag ? score : 0).append(' ');
            edges.removeFirst();
        }
        io.write(res);
    }

    private static int find(int x) {
        if (par[x] == -1)
            par[x] = x;
        if (x == par[x])
            return x;
        return par[x] = find(par[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y)
            par[y] = x;
    }

}

class Edge implements Comparable<Edge> {
    int v1, v2, c;

    public Edge(int v1, int v2, int c) {
        this.v1 = v1;
        this.v2 = v2;
        this.c = c;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.c - edge.c;
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