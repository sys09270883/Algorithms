import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            N = io.nextInt();   M = io.nextInt();
            arr = new int[N + M + 1];
            tree = new int[1 << (int)Math.ceil(Math.log(N + M) / Math.log(2)) + 1];

            for (int i = M + 1; i < N + M + 1; i++) {
                update(i, 1, 1, 1, N + M);
                arr[i - M] = i;
            }

            int idx = M;
            for (int i = 0; i < M; i++) {
                int t = io.nextInt();
                res.append(query(1, arr[t] - 1, 1, 1,N + M)).append(' ');
                update(arr[t], 0, 1, 1, N + M);
                arr[t] = idx--;
                update(arr[t], 1, 1, 1, N + M);
            }

            res.append('\n');
        }

        io.write(res);
    }

    private static int update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] = v;
        int m = s + e >>> 1;
        return tree[n] = update(i, v, n * 2, s, m) + update(i, v, n * 2 + 1, m + 1, e);
    }

    private static int query(int l, int r, int n, int s, int e) {
        if (l > e || r < s)
            return 0;
        if (l <= s && e <= r)
            return tree[n];
        int m = s + e >>> 1;
        return query(l, r, 2 * n, s, m) + query(l, r, 2 * n + 1, m + 1, e);
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