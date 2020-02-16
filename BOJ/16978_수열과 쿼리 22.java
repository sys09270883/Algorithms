import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr;
    static long[] tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        tree = new long[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
        List<Query> list = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        init(1, 1, N);
        M = io.nextInt();
        int t1 = 1, t2 = 1;
        for (int i = 0; i < M; i++) {
            Query query = new Query();
            query.q = io.nextInt();
            if (query.q == 1) {
                query.kth = t1++;
                query.a = io.nextInt(); query.b = io.nextInt();
            }
            else {
                query.idx = t2++;
                query.kth = io.nextInt();   query.a = io.nextInt(); query.b = io.nextInt();
            }
            list.add(query);
        }
        long[] ans = new long[t2];
        Collections.sort(list);
        for (Query q : list) {
            if (q.q == 1)
                update(q.a, q.b, 1, 1, N);
            else
                ans[q.idx] = query(q.a, q.b, 1, 1, N);
        }
        for (int i = 1; i < t2; i++) {
            res.append(ans[i]).append('\n');
        }
        io.write(res);
    }

    private static long init(int n, int s, int e) {
        if (s == e)
            return tree[n] = arr[s];
        int m = s + e >>> 1;
        return tree[n] = init(2 * n, s, m) + init(2 * n + 1, m + 1, e);
    }

    private static long update(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return tree[n];
        if (s == e)
            return tree[n] = v;
        int m = s + e >>> 1;
        return tree[n] = update(i, v, 2 * n, s, m) + update(i, v, 2 * n + 1, m + 1, e);
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

class Query implements Comparable<Query> {
    int kth, idx, a, b, q;

    @Override
    public int compareTo(Query query) {
        if (kth == query.kth)
            return q - query.q;
        return kth - query.kth;
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