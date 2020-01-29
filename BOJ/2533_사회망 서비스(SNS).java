import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static List<List<Integer>> tree;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        tree = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            int n1 = io.nextInt(), n2 = io.nextInt();
            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }

        DFS(1);

        io.write(Math.min(dp[1][0], dp[1][1]));
    }

    private static void DFS(int cur) {
        visited[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (Integer next : tree.get(cur)) {
            if (!visited[next]) {
                DFS(next);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
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