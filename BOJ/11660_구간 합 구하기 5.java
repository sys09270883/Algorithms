import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[][] arr, dp;

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), M = io.nextInt();
        arr = new int[N + 1][N + 1];    dp = new int[N + 1][N + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = io.nextInt();
            }
        }
        for (int i = 1; i < N + 1; i++) {
            dp[1][i] = dp[1][i - 1] + arr[1][i];
            dp[i][1] = dp[i - 1][1] + arr[i][1];
        }
        for (int i = 2; i < N + 1; i++) {
            for (int j = 2; j < N + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            res.append(query(io.nextInt(), io.nextInt(), io.nextInt(), io.nextInt())).append('\n');
        }

        io.write(res);
    }

    private static int query(int x1, int y1, int x2, int y2) {
        return dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
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