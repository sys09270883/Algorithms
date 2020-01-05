import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, a, b;
    static List<List<Integer>> adj;
    static int[] tree;
    static int[][] parent;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        tree = new int[N + 1];
        parent = new int[20][N + 1];
        for (int[] row : parent) {
            Arrays.fill(tree, -1);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            a = io.nextInt();   b = io.nextInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        DFS(1, 0);
        for (int i = 0; i < 19; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (parent[i][j] != -1)
                    parent[i + 1][j] = parent[i][parent[i][j]];
            }
        }

        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            a = io.nextInt();   b = io.nextInt();
            res.append(LCA(a, b)).append('\n');
        }
        io.write(res);
    }

    private static void DFS(int node, int depth) {
        if (tree[node] != -1)
            return;

        tree[node] = depth;
        for (Integer next : adj.get(node)) {
            if (tree[next] != -1)
                continue;
            parent[0][next] = node;
            for (int i = 1; i < 20; i++) {
                if (parent[i - 1][next] == 0)
                    break;
                parent[i][next] = parent[i - 1][parent[i - 1][next]];
            }

            DFS(next, depth + 1);
        }
    }

    private static int LCA(int a, int b) {
        if (tree[a] > tree[b]) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        for (int i = 19; i >= 0; i--) {
            if (tree[b] - tree[a] >= (1 << i))
                b = parent[i][b];
        }
        if (a == b)
            return a;
        for (int i = 19; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
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
