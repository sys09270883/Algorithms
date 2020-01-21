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
            int q = io.nextInt(), a = io.nextInt(), b = io.nextInt();
            if (q == 1) {
                arr[a] = b;
                update(a, b, 1, 1, N);
            }
            else
                res.append(getMin(a, b, 1, 1, N)).append('\n');
        }

        io.write(res);
    }

    private static int minIdx(int a, int b) {
        if (a == -1)
            return b;
        if (b == -1)
            return a;
        return arr[a] <= arr[b] ? a : b;
    }

    private static int init(int node, int start, int end) {
        if (start == end)
            return tree[node] = start;
        int m = (start + end) / 2;
        return tree[node] = minIdx(init(node * 2, start, m), init(node * 2 + 1, m + 1, end));
    }

    private static int update(int idx, int val, int node, int start, int end) {
        if (idx < start || idx > end || start == end)
            return tree[node];
        int m = (start + end) / 2;
        return tree[node] = minIdx(update(idx, val, node * 2, start, m), update(idx, val, node * 2 + 1, m + 1, end));
    }

    private static int getMin(int left, int right, int node, int start, int end) {
        if (right < start || left > end)
            return -1;
        if (left <= start && end <= right)
            return tree[node];
        int m = (start + end) / 2;
        return minIdx(getMin(left, right, node * 2, start, m), getMin(left, right, node * 2 + 1, m + 1, end));
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