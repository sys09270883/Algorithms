import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int[] cost, sum;
    static int[][] dp;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            int K = io.nextInt();
            sum = new int[K + 1];
            cost = new int[K + 1];
            dp = new int[K + 1][K + 1];
            for (int[] row : dp) {
                Arrays.fill(row, INF);
            }
            for (int i = 1; i < K + 1; i++) {
                cost[i] = io.nextInt();
                sum[i] = sum[i - 1] + cost[i];
            }
            res.append(func(1, K)).append('\n');
        }

        io.write(res);
    }

    private static int func(int x, int y) {
        if (dp[x][y] != INF)
            return dp[x][y];
        if (x == y)
            return dp[x][y] = 0;
        if (x + 1 == y)
            return dp[x][y] = cost[x] + cost[y];

        for (int i = x; i < y; i++) {
            int left = func(x, i);
            int right = func(i + 1, y);
            dp[x][y] = Math.min(dp[x][y], left + right);
        }

        return dp[x][y] += sum[y] - sum[x - 1];
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