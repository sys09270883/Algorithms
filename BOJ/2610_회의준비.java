import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = 987654321;
    static int N, K;
    static int[][] adj;
    static int[] parent, cost;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   K = io.nextInt();
        adj = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                adj[i][j] = i == j ? 0 : INF;
            }
        }
        parent = new int[N + 1];
        Arrays.setAll(parent, i -> i);
        cost = new int[N + 1];
        Map<Integer, Node> tm = new TreeMap<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int a = io.nextInt(), b = io.nextInt();
            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        floyd();

        for (int i = 1; i < N + 1; i++) {
            for (int j = i; j < N + 1; j++) {
                if (i == j || adj[i][j] == INF)
                    continue;
                union(i, j);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j || adj[i][j] == INF)
                    continue;
                cost[i] = Math.max(cost[i], adj[i][j]);
            }

            if (tm.containsKey(parent[i])) {
                if (cost[i] < tm.get(parent[i]).val)
                    tm.put(parent[i], new Node(cost[i], i));
            }
            else
                tm.put(parent[i], new Node(cost[i], i));
        }

        res.append(tm.size()).append('\n');
        List<Integer> list = new ArrayList<>();
        for (Node n : tm.values()) {
            list.add(n.idx);
        }
        Collections.sort(list);

        for (Integer i : list) {
            res.append(i).append('\n');
        }

        io.write(res);
    }

    private static void floyd() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y)
            parent[y] = x;
    }

}

class Node {
    int val, idx;

    public Node(int val, int idx) {
        this.val = val;
        this.idx = idx;
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
