import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = (int)1e9;
    static int n, m, h;
    static int[] arr;
    static List<List<Integer>> tree;

    public static void main(String... args) throws IOException {
        n = io.nextInt();
        arr = new int[n + 1];
        h = 1 << (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
        tree = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            tree.add(new ArrayList<>());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            arr[i] = io.nextInt();
            update(1, arr[i], i, 1, n);
        }
        for (int i = 0; i < h; i++) {
            Collections.sort(tree.get(i));
        }
        m = io.nextInt();

        while (m-- > 0) {
            int a = io.nextInt(), b = io.nextInt(), c = io.nextInt();
            res.append(query(1, c, a, b, 1, n)).append('\n');
        }

        io.write(res);
    }

    private static int upperBound(int bucket, int val) {
        int low = 0, high = tree.get(bucket).size();
        while (low < high) {
            int m = (low + high) / 2;
            if (tree.get(bucket).get(m) <= val)
                low = m + 1;
            else
                high = m;
        }
        return high;
    }

    private static int query(int bucket, int val, int left, int right, int start, int end) {
        if (left > end || right < start)
            return 0;
        if (left <= start && end <= right)
            return tree.get(bucket).size() - upperBound(bucket, val);
        int m = (start + end) >>> 1;
        return query(bucket * 2, val, left, right, start, m) + query(bucket * 2 + 1, val, left, right, m + 1, end);
    }

    private static void update(int bucket, int val, int node, int start, int end) {
        if (node < start || node > end)
            return;
        tree.get(bucket).add(val);
        if (start == end)
            return;
        int m = (start + end) >>> 1;
        update(bucket * 2, val, node, start, m);
        update(bucket * 2 + 1, val, node, m + 1, end);
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