import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, K;
    static int[] tree;

    public static void main(String... args) throws IOException {
        String in = null;
        StringBuilder res = new StringBuilder();

        while ((in = io.nextLine()) != null) {
            io.st = new StringTokenizer(in);
            N = io.nextInt();
            K = io.nextInt();
            tree = new int[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
            for (int i = 1; i < N + 1; i++) {
                int x = io.nextInt();
                x = x > 0 ? 1 : x == 0 ? 0 : -1;
                update(i, x, 1, 1, N);
            }

            for (int i = 0; i < K; i++) {
                char q = io.next().charAt(0);
                int a = io.nextInt(), b = io.nextInt();

                if (q == 'C') {
                    b = b > 0 ? 1 : b == 0 ? 0 : -1;
                    update(a, b, 1, 1, N);
                }
                else {
                    int p = query(a, b, 1, 1, N);
                    res.append(p > 0 ? '+' : p == 0 ? '0' : '-');
                }
            }
            res.append('\n');
        }

        io.write(res);
    }

    private static int update(int idx, int val, int node, int start, int end) {
        if (idx > end || idx < start)
            return tree[node];
        if (start == end)
            return tree[node] = val;
        int m = (start + end) >>> 1;
        return tree[node] = update(idx, val, node * 2, start, m)
                * update(idx, val, node * 2 + 1, m + 1, end);
    }

    private static int query(int left, int right, int node, int start, int end) {
        if (left > end || right < start)
            return 1;
        if (left <= start && end <= right)
            return tree[node];
        int m = (start + end) >>> 1;
        return query(left, right, node * 2, start, m) * query(left, right, node * 2 + 1, m + 1, end);
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