import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, K, res = 987654321;
    static int[][] arr;
    static boolean[] vis;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   K = io.nextInt();
        arr = new int[N][N];
        vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = io.nextInt();
            }
        }
        floyd();
        vis[K] = true;
        DFS(1, K, 0);
        io.write(res);
    }

    private static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }

    private static void DFS(int depth, int idx, int sum) {
        if (sum > res)
            return;
        if (depth == N) {
            res = Math.min(res, sum);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (i != idx && !vis[i]) {
                vis[i] = true;
                DFS(depth + 1, i, sum + arr[idx][i]);
                vis[i] = false;
            }
        }
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