import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int B, P, Q;
    static long[] tree;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();
        while (T-- > 0) {
            B = io.nextInt();   P = io.nextInt();   Q = io.nextInt();
            tree = new long[1 << (int)Math.ceil(Math.log(B) / Math.log(2)) + 1];
            for (int i = 0; i < P + Q; i++) {
                char c = io.next().charAt(0);
                int a = io.nextInt(), b = io.nextInt();
                if (c == 'P')
                    update(a, b, 1, 1, B);
                else
                    res.append(query(a, b, 1, 1, B)).append('\n');
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

    private static long query(int l, int r, int n, int s, int e) {
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