import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        tree = new int[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }

        init(1, 1, N);

        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            int a = io.nextInt(), b = io.nextInt(), c = io.nextInt();
            if (a == 1) {
                if ((arr[b] & 1) == 1 && (c & 1) == 0)
                    update(b, -1, 1, 1, N);
                else if ((arr[b] & 1) == 0 && (c & 1) == 1)
                    update(b, 1, 1, 1, N);
                arr[b] = c;
            }
            else if (a == 2)
                res.append(c - b + 1 - query(b, c, 1, 1, N)).append('\n');
            else if (a == 3)
                res.append(query(b, c, 1, 1, N)).append('\n');
        }

        io.write(res);
    }

    private static int init(int n, int s, int e) {
        if (s == e)
            return tree[n] = (arr[s] & 1) == 1 ? 1 : 0;
        int m = s + e >>> 1;
        return tree[n] = init(2 * n, s, m) + init(2 * n + 1, m + 1, e);
    }

    private static int update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] += v;
        int m = s + e >>> 1;
        return tree[n] = update(i, v, 2 * n, s, m) + update(i, v, 2 * n + 1, m + 1, e);
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