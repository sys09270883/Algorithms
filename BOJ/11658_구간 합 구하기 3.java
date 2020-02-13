import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[][] arr, dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        arr = new int[N + 1][N + 1];    dp = new int[N + 1][N + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = io.nextInt();
                dp[i][j] = dp[i][j - 1] + arr[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            int w = io.nextInt();
            if (w == 0) {
                int x = io.nextInt(), y = io.nextInt(), z = io.nextInt(), diff = z - arr[x][y];
                arr[x][y] = z;
                for (int j = y; j < N + 1; j++) {
                    dp[x][j] += diff;
                }
            }
            else {
                int x1 = io.nextInt(), y1 = io.nextInt(), x2 = io.nextInt(), y2 = io.nextInt(), sum = 0;
                for (int j = x1; j <= x2; j++) {
                    sum += dp[j][y2] - dp[j][y1 - 1];
                }
                res.append(sum).append('\n');
            }
        }

        io.write(res);
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