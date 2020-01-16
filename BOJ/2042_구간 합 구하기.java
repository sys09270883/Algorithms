import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, K, a, b, c;
    static int[] arr;
    static long[] tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();   K = io.nextInt();
        arr = new int[N + 1];
        tree = new long[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }

        init(1, 1, N);
        for (int i = 0; i < M + K; i++) {
            a = io.nextInt();   b = io.nextInt();   c = io.nextInt();
            if (a == 1) {
                int diff = c - arr[b];
                arr[b] = c;
                update(1, 1, N, b, diff);
            }
            else
                res.append(sum(b, c, 1, 1, N)).append('\n');
        }

        io.write(res);
    }

    private static long init(int node, int start, int end) {
        if (start == end)
            return tree[node] = arr[start];
        int m = (start + end) / 2;
        return tree[node] = init(node * 2, start, m) + init(node * 2 + 1, m + 1, end);
    }

    private static void update(int node, int start, int end, int idx, int diff) {
        if (!(start <= idx && idx <= end))
            return;
        tree[node] += diff;
        if (start == end)
            return;
        int m = (start + end) / 2;
        update(node * 2, start, m, idx, diff);
        update(node * 2 + 1, m + 1, end, idx, diff);
    }

    private static long sum(int left, int right, int node, int start, int end) {
        if (left <= start && end <= right)
            return tree[node];
        if (right < start || end < left)
            return 0;
        int m = (start + end) / 2;
        return sum(left, right, node * 2, start, m) + sum(left, right, node * 2 + 1, m + 1, end);
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

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
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