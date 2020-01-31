import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, K, X;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();   K = io.nextInt();   X = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 2; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < M; i++) {
            adj.get(io.nextInt()).add(io.nextInt());
        }

        List<Integer> list = BFS(X);

        if (list.isEmpty())
            res.append(-1);
        else {
            Collections.sort(list);
            for (Integer i : list) {
                res.append(i).append('\n');
            }
        }
        io.write(res);
    }

    private static List<Integer> BFS(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int dist = tmp.dist;

            if (dist >= K) {
                if (dist > K)
                    continue;
                list.add(cur);
            }

            for (Integer next : adj.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new Node(next, dist + 1));
                }
            }
        }

        return list;
    }

}

class Node {
    int idx, dist;

    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
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