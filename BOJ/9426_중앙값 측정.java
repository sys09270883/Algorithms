import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MAX = 250000;
    static int N, K;
    static long res;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   K = io.nextInt();
        arr = new int[N + 1];
        tree = new int[1 << (int)Math.ceil(Math.log(MAX) / Math.log(2)) + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        for (int i = 1; i < K + 1; i++) {
            update(arr[i], 1, 1, 0, MAX);
        }
        for (int i = K; i < N + 1; i++) {
            res += query((K + 1) / 2, 1, 0, MAX);
            if (i == N)
                break;
            update(arr[i + 1], 1, 1, 0, MAX);
            update(arr[i - K + 1], -1, 1, 0, MAX);
        }
        io.write(res);
    }

    private static int update(int i, int d, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] += d;
        int m = s + e >>> 1;
        return tree[n] = update(i, d, 2 * n, s, m) + update(i, d, 2 * n + 1, m + 1, e);
    }

    private static int query(int k, int n, int s, int e) {
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