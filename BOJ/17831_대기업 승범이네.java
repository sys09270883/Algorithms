import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[] val, parent;
    static int[][] dp;
    static List<List<Integer>> tree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        val = new int[N + 1];
        parent = new int[N + 1];
        dp = new int[N + 1][2];
        tree = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 2; i < N + 1; i++) {
            parent[i] = io.nextInt();
            tree.get(parent[i]).add(i);
        }
        for (int i = 1; i < N + 1; i++) {
            val[i] = io.nextInt();
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }

        io.write(func(1, 0));
    }

    private static int func(int n, int check) {
        if (dp[n][check] != -1)
            return dp[n][check];
        if (check == 1) {
            dp[n][check] = 0;
            for (Integer i : tree.get(n)) {
                dp[n][check] += func(i, 0);
            }
        }
        else {
            dp[n][check] = 0;
            int a = 0, b = 0;
            for (Integer i : tree.get(n)) {
                a += func(i, 0);
            }
            for (Integer i : tree.get(n)) {
                b = a - func(i, 0) + func(i, 1) + val[n] * val[i];
                dp[n][check] = Math.max(dp[n][check], b);
            }
            dp[n][check] = Math.max(dp[n][check], a);
        }
        return dp[n][check];
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

    void write(char c) throws IOException {
        bw.write(c);
        close();
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