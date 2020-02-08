import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, q, a, b;
    static int[] parent, dist;

    public static void main(String... args) throws IOException {
        StringBuilder res = new StringBuilder();

        while ((N = io.nextInt()) != 0) {
            M = io.nextInt();
            parent = new int[N + 1];
            Arrays.setAll(parent, i -> i);
            dist = new int[N + 1];

            for (int i = 0; i < M; i++) {
                q = io.next().charAt(0);    a = io.nextInt();   b = io.nextInt();
                if (q == '!')
                    union(a, b, io.nextInt());
                else
                    res.append(find(a) == find(b) ? dist[b] - dist[a] : "UNKNOWN").append('\n');
            }
        }

        io.write(res);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        int p = find(parent[x]);
        dist[x] += dist[parent[x]];
        return parent[x] = p;
    }

    private static void union(int x, int y, int diff) {
        int px = find(x);
        int py = find(y);
        if (x == y)
            return;
        parent[py] = px;
        dist[py] = dist[x] - dist[y] + diff;
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