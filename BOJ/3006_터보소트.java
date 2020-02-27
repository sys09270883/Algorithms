import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MAX = (int)1e5;
    static int N, H, f, r, p;
    static int[] tree;
    static Node[] arr;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   f = 1;  r = N;
        arr = new Node[N + 1];
        H = 1 << (int)Math.ceil(Math.log(MAX) / Math.log(2)) + 1;
        tree = new int[H];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = new Node(i, io.nextInt());
            update(i, 1, 1, 1, MAX);
        }
        Arrays.sort(arr, 1, N + 1);
        for (int i = 1; i < N + 1; i++) {
            if ((i & 1) == 1) {
                p = arr[f++].idx;
                res.append(query(1, p - 1, 1, 1, MAX)).append('\n');
            }
            else {
                p = arr[r--].idx;
                res.append(query(p + 1, N, 1, 1, MAX)).append('\n');
            }
            update(p, 0, 1, 1, MAX);
        }
        io.write(res);
    }

    private static int update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] = v;
        int m = s + e >>> 1;
        return tree[n] = update(i, v, 2 * n, s, m) + update(i, v, 2 * n + 1, m + 1, e);
    }

    private static int query(int l, int r, int n, int s, int e) {
        if (l > e || r < s)
            return 0;
        if (l <= s && e <= r)
            return tree[n];
        int m = s + e >>> 1;
        return tree[n] = query(l, r, 2 * n, s, m) + query(l, r, 2 * n + 1, m + 1, e);
    }

}

class Node implements Comparable<Node> {
    int idx, val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node node) {
        return this.val - node.val;
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