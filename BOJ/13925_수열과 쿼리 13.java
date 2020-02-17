import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MOD = (int)1e9 + 7;
    static int N, M, H;
    static int[] arr;
    static long[] tree;
    static long[][] lazy;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        H = 1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
        tree = new long[H];
        lazy = new long[H][2];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        for (int i = 1; i < H; i++) {
            lazy[i][0] = 1;
            lazy[i][1] = 0;
        }
        init(1, 1, N);
        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            int q = io.nextInt(), l = io.nextInt(), r = io.nextInt();
            if (q == 1)
                update(l, r, 1, io.nextInt(), 1, 1, N);
            else if (q == 2)
                update(l, r, io.nextInt(), 0, 1, 1, N);
            else if (q == 3)
                update(l, r, 0, io.nextInt(), 1, 1, N);
            else if (q == 4)
                res.append(query(l, r, 1, 1, N)).append('\n');
        }
        io.write(res);
    }

    private static long init(int n, int s, int e) {
        if (s == e)
            return tree[n] = arr[s];
        int m = s + e >>> 1;
        return tree[n] = (init(2 * n, s, m) + init(2 * n + 1, m + 1, e)) % MOD;
    }

    private static void updateLazy(int n, int s, int e) {
        if (lazy[n][0] == 1 && lazy[n][1] == 0)
            return;
        if (s != e) {
            for (int i = 2 * n; i <= 2 * n + 1; i++) {
                lazy[i][0] = lazy[n][0] * lazy[i][0];   lazy[i][0] %= MOD;
                lazy[i][1] = lazy[n][0] * lazy[i][1] + lazy[n][1];   lazy[i][1] %= MOD;
            }
        }
        tree[n] = lazy[n][0] * tree[n] + (e - s + 1) * lazy[n][1];  tree[n] %= MOD;
        lazy[n][0] = 1; lazy[n][1] = 0;
    }

    private static void update(int l, int r, long mul, long sum, int n, int s, int e) {
        updateLazy(n, s, e);
        if (l > e || r < s)
            return;
        if(l <= s && e <= r){
            lazy[n][0] *= mul; lazy[n][0] %= MOD;
            lazy[n][1] *= mul; lazy[n][1] %= MOD;
            lazy[n][1] += sum; lazy[n][1] %= MOD;
            updateLazy(n, s, e);
            return;
        }
        int m = s + e >>> 1;
        update(l, r, mul, sum, 2 * n, s, m);
        update(l, r, mul, sum, 2 * n + 1, m + 1, e);
        tree[n] = (tree[2 * n] + tree[2 * n + 1]) % MOD;
    }

    private static long query(int l, int r, int n, int s, int e) {
        updateLazy(n, s, e);
        if (l > e || r < s)
            return 0;
        if (l <= s && e <= r)
            return tree[n];
        int m = s + e >>> 1;
        return (query(l, r, 2 * n, s, m) + query(l, r, 2 * n + 1, m + 1, e)) % MOD;
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