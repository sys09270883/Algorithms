import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static List<List<Integer>> adj;
    static List<Integer> destroyed, ans;
    static int[] parent;
    static int N, M, U, V, K, P, dtr;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        parent = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.setAll(parent, i -> i);
        destroyed = new ArrayList<>();
        ans = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            U = io.nextInt();
            V = io.nextInt();
            adj.get(U).add(V);
            adj.get(V).add(U);
        }
        K = io.nextInt();
        dtr = io.nextInt();
        destroyed.add(dtr);
        for (int i = 0; i < K - 1; i++) {
            P = io.nextInt();
            union(dtr, P);
            destroyed.add(P);
        }

        for (int i = 1; i < N + 1; i++) {
            if (find(i) != dtr)
                continue;

            boolean flag = true;

            for (int j : adj.get(i)) {
                if (find(j) != dtr) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                ans.add(i);
        }

        if (ans.isEmpty())
            res.append(-1);

        else {
            Set<Integer> hs = new HashSet<>();

            for (Integer i : ans) {
                hs.add(i);
                boolean flag = true;

                for (Integer j : adj.get(i)) {
                    if (find(j) != dtr) {
                        flag = false;
                        break;
                    }
                }

                if (flag)
                    for (Integer j : adj.get(i)) {
                        hs.add(j);
                    }
            }

            if (hs.size() < K) {
                io.write(-1);
                return;
            }
        }

        if (!ans.isEmpty()) {
            res.append(ans.size()).append('\n');

            for (int i : ans) {
                res.append(i).append(' ');
            }
        }

        io.write(res);
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