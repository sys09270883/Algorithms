import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MAX = 65536;
    static int N, K;
    static long res;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   K = io.nextInt();
        arr = new int[N + 1];
        tree = new int[MAX + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }

        for (int i = 1; i < K + 1; i++) {
            update(arr[i], 1);
        }
        res = mid();

        for (int i = 1; i <= N - K; i++) {
            update(arr[i], -1);
            update(arr[i + K], 1);
            res += mid();
        }

        io.write(res);
    }

    private static long sum(int idx) {
        idx++;
        long ret = 0;
        while (idx > 0) {
            ret += tree[idx];
            idx -= (idx & -idx);
        }
        return ret;
    }

    private static void update(int idx, int diff) {
        idx++;
        while (idx <= MAX) {
            tree[idx] += diff;
            idx += (idx & -idx);
        }
    }

    private static long mid() {
        int low = 0, high = MAX, C = (K + 1) / 2;
        while (low <= high) {
            int m = (low + high) / 2;
            if (sum(m) < C)
                low = m + 1;
            else
                high = m - 1;
        }
        return low;
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