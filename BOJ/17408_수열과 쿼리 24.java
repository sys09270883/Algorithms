import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, H;
    static int[] arr;
    static Pair[] tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        tree = new Pair[H = 1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        init(1, 1, N);
        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            int q = io.nextInt(), l = io.nextInt(), r = io.nextInt();
            if (q == 1)
                update(l, r, 1, 1, N);
            else {
                Pair first = query(l, r, 1, 1, N);
                Pair left = query(l, first.second - 1, 1, 1, N);
                Pair right = query(first.second + 1, r, 1, 1, N);
                res.append(first.first + Math.max(left.first, right.first)).append('\n');
            }
        }
        io.write(res);
    }

    private static Pair init(int n, int s, int e) {
        if (s == e)
            return tree[n] = new Pair(arr[s], s);
        int m = s + e >>> 1;
        Pair left = init(2 * n, s, m), right = init(2 * n + 1, m + 1, e);
        return tree[n] = left.first > right.first ? left : right;
    }

    private static Pair update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e) {
            tree[n].first = v;
            tree[n].second = i;
            return tree[n];
        }
        int m = s + e >>> 1;
        Pair left = update(i, v, 2 * n, s, m), right = update(i, v, 2 * n + 1, m + 1, e);
        return tree[n] = left.first > right.first ? left : right;
    }

    private static Pair query(int l, int r, int n, int s, int e) {
        if (l > e || r < s)
            return new Pair(0, -1);
        if (l <= s && e <= r)
            return tree[n];
        int m = s + e >>> 1;
        Pair left = query(l, r, 2 * n, s, m), right = query(l, r, 2 * n + 1, m + 1, e);
        return left.first > right.first ? left : right;
    }

}

class Pair {
    int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
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