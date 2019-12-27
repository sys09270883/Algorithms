import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), M = io.nextInt();
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = io.nextInt();
            }
        }

        dp[0][0] = map[0][0];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + 1 < N)
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + map[i + 1][j]);
                if (j + 1 < M)
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + map[i][j + 1]);
                if (i + 1 < N && j + 1 < M)
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + map[i + 1][j + 1]);
            }
        }

        io.write(dp[N - 1][M - 1]);
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

    void write(char[] str) throws IOException {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            bw.write(str[i]);
        }
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}