import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), M = io.nextInt(), ans = 0;
        int[][] arr = new int[M][N], dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = io.nextInt();
            }
        }
        for (int i = 0; i < M; i++) {
            dp[i][0] = arr[i][0];
        }

        for (int k = 0; k < N - 1; k++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    if (i == j)
                        dp[j][k + 1] = Math.max(dp[j][k + 1], dp[i][k] + arr[j][k + 1] / 2);
                    else
                        dp[j][k + 1] = Math.max(dp[j][k + 1], dp[i][k] + arr[j][k + 1]);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, dp[i][N - 1]);
        }

        io.write(ans);
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
