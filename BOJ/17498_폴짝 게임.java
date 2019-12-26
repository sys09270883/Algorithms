import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, D;
    static long ans = Long.MIN_VALUE;
    static long[][] arr, dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        D = io.nextInt();
        arr = new long[N][M];
        dp = new long[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = io.nextLong();
                dp[i][j] = Long.MIN_VALUE;
            }
        }

        Arrays.fill(dp[0], 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < D; d++) {
                    for (int k = 0; k <= d; k++) {
                        if (j + d - k >= 0 && j + d - k < M && i + 1 + k < N) {
                            dp[i + 1 + k][j + d - k] = Math.max(dp[i + 1 + k][j + d - k]
                                    , dp[i][j] + arr[i][j] * arr[i + 1 + k][j + d - k]);
                        }

                        if (j - d + k >= 0 && j - d + k < M && i + 1 + k < N) {
                            dp[i + 1 + k][j - d + k] = Math.max(dp[i + 1 + k][j - d + k]
                                    , dp[i][j] + arr[i][j] * arr[i + 1 + k][j - d + k]);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, dp[N - 1][i]);
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