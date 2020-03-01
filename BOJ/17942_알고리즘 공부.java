import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static List<List<Node>> adj;
    static int[] costs, arr;
    static boolean[] visited;
    static int N, M, K, A, B, D;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        arr = new int[N + 1];
        costs = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        visited = new boolean[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        K = io.nextInt();
        for (int i = 0; i < K; i++) {
            A = io.nextInt();
            B = io.nextInt();
            D = io.nextInt();
            adj.get(A).add(new Node(B, D));
        }

        int low = 1, high = (int) 1e8, res = (int) 1e8;
        while (low <= high) {
            int m = (low + high) / 2;
            if (BFS(m)) {
                res = Math.min(res, m);
                high = m - 1;
            }
            else
                low = m + 1;
        }

        io.write(res);
    }

    private static boolean BFS(int m) {
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            costs[i] = arr[i];
            visited[i] = false;
            if (arr[i] <= m) {
                queue.add(i);
                cnt++;
                visited[i] = true;
                if (cnt >= M)
                    return true;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Node n : adj.get(cur)) {
                int next = n.idx;
                int nextCost = n.cost;

                if (visited[next])
                    continue;
                costs[next] -= nextCost;
                if (costs[next] <= m) {
                    queue.add(next);
                    visited[next] = true;
                    cnt++;
                    if (cnt >= M)
                        return true;
                }
            }
        }

        return false;
    }

}

class Node {
    int idx, cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
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
