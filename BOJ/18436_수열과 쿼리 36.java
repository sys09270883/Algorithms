import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, H;
    static int[] arr, odd, even;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        H = 1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
        odd = new int[H];   even = new int[H];
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }

        initOdd(1, 1, N);
        initEven(1, 1, N);

        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            int a = io.nextInt(), b = io.nextInt(), c = io.nextInt();
            if (a == 1) {
                if ((arr[b] & 1) == 1 && (c & 1) == 0) {
                    updateEven(b, 1, 1, 1, N);
                    updateOdd(b, -1, 1, 1, N);
                }
                else if ((arr[b] & 1) == 0 && (c & 1) == 1) {
                    updateEven(b, -1, 1, 1, N);
                    updateOdd(b, 1, 1, 1, N);
                }
                arr[b] = c;
            }
            else if (a == 2)
                res.append(queryEven(b, c, 1, 1, N)).append('\n');
            else if (a == 3)
                res.append(queryOdd(b, c, 1, 1, N)).append('\n');
        }

        io.write(res);
    }

    private static int initOdd(int n, int s, int e) {
        if (s == e)
            return odd[n] = (arr[s] & 1) == 1 ? 1 : 0;
        int m = s + e >>> 1;
        return odd[n] = initOdd(2 * n, s, m) + initOdd(2 * n + 1, m + 1, e);
    }

    private static int initEven(int n, int s, int e) {
        if (s == e)
            return even[n] = (arr[s] & 1) == 0 ? 1 : 0;
        int m = s + e >>> 1;
        return even[n] = initEven(2 * n, s, m) + initEven(2 * n + 1, m + 1, e);
    }

    private static int updateOdd(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return odd[n];
        if (s == e)
            return odd[n] += v;
        int m = s + e >>> 1;
        return odd[n] = updateOdd(i, v, 2 * n, s, m) + updateOdd(i, v, 2 * n + 1, m + 1, e);
    }

    private static int updateEven(int i, int v, int n, int s, int e) {
        if (i > e || i < s)
            return even[n];
        if (s == e)
            return even[n] += v;
        int m = s + e >>> 1;
        return even[n] = updateEven(i, v, 2 * n, s, m) + updateEven(i, v, 2 * n + 1, m + 1, e);
    }

    private static int queryOdd(int l, int r, int n, int s, int e) {
        if (l > e || r < s)
            return 0;
        if (l <= s && e <= r)
            return odd[n];
        int m = s + e >>> 1;
        return queryOdd(l, r, 2 * n, s, m) + queryOdd(l, r, 2 * n + 1, m + 1, e);
    }

    private static int queryEven(int l, int r, int n, int s, int e) {
        if (l > e || r < s)
            return 0;
        if (l <= s && e <= r)
            return even[n];
        int m = s + e >>> 1;
        return queryEven(l, r, 2 * n, s, m) + queryEven(l, r, 2 * n + 1, m + 1, e);
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