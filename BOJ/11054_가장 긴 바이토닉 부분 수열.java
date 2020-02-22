import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, res;
    static int[] arr;
    static int[][] dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        dp = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            dp[i][0] = 1;
            for (int j = 0; j <= i; j++) {
                if (arr[j] < arr[i] && dp[i][0] < dp[j][0] + 1)
                    dp[i][0]++;
            }
        }
        for (int i = N; i > 0; i--) {
            dp[i][1] = 1;
            for (int j = N; j > i; j--) {
                if (arr[j] < arr[i] && dp[i][1] < dp[j][1] + 1)
                    dp[i][1]++;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            res = Math.max(res, dp[i][0] + dp[i][1]);
        }
        io.write(res - 1);
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