import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] arr;
    static long[] minTree, maxTree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        minTree = new long[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        maxTree = new long[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        StringBuilder res = new StringBuilder();

        minInit(1, 1, N);
        maxInit(1, 1, N);
        for (int i = 0; i < M; i++) {
            int left = io.nextInt(), right = io.nextInt();
            res.append(min(left, right, 1, 1, N)).append(' ');
            res.append(max(left, right, 1, 1, N)).append('\n');
        }

        io.write(res);
    }

    private static long minInit(int node, int start, int end) {
        if (start == end)
            return minTree[node] = arr[start];
        int m = (start + end) / 2;
        return minTree[node] = Math.min(minInit(node * 2, start, m), minInit(node * 2 + 1, m + 1, end));
    }

    private static long maxInit(int node, int start, int end) {
        if (start == end)
            return maxTree[node] = arr[start];
        int m = (start + end) / 2;
        return maxTree[node ] = Math.max(maxInit(node * 2, start, m), maxInit(node * 2 + 1, m + 1, end));
    }

    private static long min(int left, int right, int node, int start, int end) {
        if (right < start || left > end)
            return INF;
        if (left <= start && end <= right)
            return minTree[node];
        int m = (start + end) / 2;
        return Math.min(min(left, right, node * 2, start, m), min(left, right, node * 2 + 1, m + 1, end));
    }

    private static long max(int left, int right, int node, int start, int end) {
        if (right < start || left > end)
            return -INF;
        if (left <= start && end <= right)
            return maxTree[node];
        int m = (start + end) / 2;
        return Math.max(max(left, right, node * 2, start, m), max(left, right, node * 2 + 1, m + 1, end));
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