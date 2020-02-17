import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = 987654321;
    static int N, end;
    static int[][] arr, dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N][N];
        dp = new int[N][1 << N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        end = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = io.nextInt();
            }
        }
        io.write(DFS(0, 1));
    }

    private static int DFS(int cur, int visited) {
        if (visited == end)
            return arr[cur][0] == 0 ? INF : arr[cur][0];
        if (dp[cur][visited] != -1)
            return dp[cur][visited];
        int ret = INF;
        for (int i = 1; i < N; i++) {
            if ((visited & (1 << i)) == 0 && arr[cur][i] != 0)
                ret = Math.min(ret, DFS(i, visited | (1 << i)) + arr[cur][i]);
        }
        return dp[cur][visited] = ret;
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