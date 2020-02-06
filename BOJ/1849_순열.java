import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        tree = new int[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            update(i, 1, 1, 1, N);
        }

        for (int i = 1; i < N + 1; i++) {
            int q = query(io.nextInt() + 1, 1, 1, N);
            arr[q] = i;
            update(q, 0, 1, 1, N);
        }

        for (int i = 1; i < N + 1; i++) {
            res.append(arr[i]).append('\n');
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
                + update(idx, val, node * 2 + 1, m + 1, end);
    }

    private static int query(int val, int node, int start, int end) {
        if (start == end)
            return start;
        int m = (start + end) >>> 1;
        if (tree[node * 2] >= val)
            return query(val, node * 2, start, m);
        return query(val - tree[node * 2], node * 2 + 1, m + 1, end);
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