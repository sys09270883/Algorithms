import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MAX = 2000000;
    static int N, T, S, K;
    static long[] tree;

    public static void main(String... args) throws IOException {
        tree = new long[1 << (int)Math.ceil(Math.log(MAX) / Math.log(2)) + 1];
        N = io.nextInt();
        StringBuilder res = new StringBuilder();
        while (N-- > 0) {
            T = io.nextInt();   S = io.nextInt();
            if (T == 1)
                update(S, 1, 1, 1, MAX);
            else {
                res.append(K = query(S, 1,1, MAX)).append('\n');
                update(K, -1, 1, 1, MAX);
            }
        }
        io.write(res);
    }

    private static long update(int i, int d, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] += d;
        int m = s + e >>> 1;
        return tree[n] = update(i, d, 2 * n, s, m) + update(i, d, 2 * n + 1, m + 1, e);
    }

    private static int query(long k, int n, int s, int e) {
        if (s == e)
            return s;
        int m = s + e >>> 1;
        if (tree[2 * n] >= k)
            return query(k, 2 * n, s, m);
        return query(k - tree[2 * n], 2 * n + 1, m + 1, e);
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