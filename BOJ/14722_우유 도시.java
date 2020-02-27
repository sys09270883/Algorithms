import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[][] arr;
    static int[][][] dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N][N];    dp = new int[N][N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = io.nextInt();
            }
        }
        if (arr[0][0] == 0)
            dp[0][0][0] = 1;
        for (int i = 1; i < N; i++) {
            int m = arr[0][i];
            dp[0][i][0] = m == 0 ? dp[0][i - 1][2] + 1 : dp[0][i - 1][0];
            dp[0][i][1] = m == 1 && dp[0][i][2] < dp[0][i][0] ? dp[0][i - 1][0] + 1 : dp[0][i - 1][1];
            dp[0][i][2] = m == 2 && dp[0][i][0] < dp[0][i][1] ? dp[0][i - 1][1] + 1 : dp[0][i - 1][2];
            m = arr[i][0];
            dp[i][0][0] = m == 0 ? dp[i - 1][0][2] + 1 : dp[i - 1][0][0];
            dp[i][0][1] = m == 1 && dp[i][0][2] < dp[i][0][0] ? dp[i - 1][0][0] + 1 : dp[i - 1][0][1];
            dp[i][0][2] = m == 2 && dp[i][0][0] < dp[i][0][1] ? dp[i - 1][0][1] + 1 : dp[i - 1][0][2];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int m = arr[i][j];
                dp[i][j][0] = m == 0
                        ? Math.max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1)
                        : Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                dp[i][j][1] = m == 1 && dp[i][j][2] < dp[i][j][0]
                        ? Math.max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1)
                        : Math.max(dp[i][j - 1][1], dp[i - 1][j][1]);
                dp[i][j][2] = m == 2 && dp[i][j][0] < dp[i][j][1]
                        ? Math.max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1)
                        : Math.max(dp[i][j - 1][2], dp[i - 1][j][2]);
            }
        }
        io.write(Math.max(dp[N - 1][N - 1][0], Math.max(dp[N - 1][N - 1][1], dp[N - 1][N - 1][2])));
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