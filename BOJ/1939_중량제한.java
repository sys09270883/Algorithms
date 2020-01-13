import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static List<List<Node>> adj;
    static boolean[] visited;
    static int N, M, A, B, C, S, E;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            A = io.nextInt();   B = io.nextInt();   C = io.nextInt();
            adj.get(A).add(new Node(B, C));
            adj.get(B).add(new Node(A, C));
        }
        S = io.nextInt();   E = io.nextInt();

        int low = 1, high = (int)1e9, res = -1;
        while (low <= high) {
            int m = (low + high) / 2;

            if (BFS(m)) {
                res = Math.max(res, m);
                low = m + 1;
            }
            else
                high = m - 1;
        }

        io.write(res);
    }

    private static boolean BFS(int weight) {
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        visited[S] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == E)
                return true;

            for (Node n : adj.get(cur)) {
                int next = n.idx;
                int nw = n.w;

                if (nw >= weight && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return false;
    }
}

class Node {
    int idx, w;

    public Node(int idx, int w) {
        this.idx = idx;
        this.w = w;
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