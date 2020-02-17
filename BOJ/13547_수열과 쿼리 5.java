import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int sz, diff;
    static int[] arr, ans, cnt;

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        sz = (int)Math.sqrt(N);
        arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        int M = io.nextInt();
        ans = new int[M + 1];
        cnt = new int[(int)1e6 + 1];
        Query[] queries = new Query[M + 1];
        queries[0] = new Query(0, 0, 0);
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < M + 1; i++) {
            queries[i] = new Query(i, io.nextInt(), io.nextInt());
        }
        Arrays.sort(queries, 1, M + 1);
        for (int i = 1; i < M + 1; i++) {
            query(queries[i - 1], queries[i]);
            ans[queries[i].idx] = diff;
        }
        for (int i = 1; i < M + 1; i++) {
            res.append(ans[i]).append('\n');
        }
        io.write(res);
    }

    private static void query(Query prev, Query cur) {
        int l1 = prev.l;
        int r1 = prev.r;
        int l2 = cur.l;
        int r2 = cur.r;
        for (int i = l1 - 1; i >= l2; i--) add(i);
        for (int i = r1 + 1; i <= r2; i++) add(i);
        for (int i = l1; i < l2; i++) erase(i);
        for (int i = r1; i > r2; i--) erase(i);
    }

    private static void add(int idx) {
        if (cnt[arr[idx]]++ == 0)
            diff++;
    }

    private static void erase(int idx) {
        if (--cnt[arr[idx]] == 0)
            diff--;
    }

}

class Query implements Comparable<Query> {
    int idx, l, r;

    public Query(int idx, int l, int r) {
        this.idx = idx;
        this.l = l;
        this.r = r;
    }

    @Override
    public int compareTo(Query query) {
        int f = this.l / Main.sz;
        int s = query.l / Main.sz;
        if (f == s)
            return this.r - query.r;
        return f - s;
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