import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int n = io.nextInt(), res = 1;
        int[] arr = new int[n + 1], dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = io.nextInt();
        }
        dp[1] = arr[1];
        int idx = 1;
        for (int i = 2; i < n + 1; i++) {
            if (dp[idx] < arr[i])
                dp[++idx] = arr[i];
            else
                dp[lowerBound(dp, 1, idx, arr[i])] = arr[i];
            res = Math.max(res, idx);
        }
        io.write(res);
    }

    private static int lowerBound(int[] arr, int s, int e, int k) {
        while (s < e) {
            int m = s + e >>> 1;
            if (arr[m] < k)
                s = m + 1;
            else
                e = m;
        }
        return s;
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