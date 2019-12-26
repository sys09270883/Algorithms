import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] dx = {-1, 1};
    static int N, M, S, E, x, y;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        S = io.nextInt();
        E = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            x = io.nextInt();
            y = io.nextInt();
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        io.write(BFS());
    }

    private static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(S, 0));
        visited[S] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int cnt = tmp.cnt;

            if (cur == E)
                return cnt;

            for (int i = 0; i < 2; i++) {
                int nx = cur + dx[i];

                if (nx < 1 || nx > N || visited[nx])
                    continue;

                visited[nx] = true;
                queue.add(new Node(nx, cnt + 1));
            }

            for (Integer i : adj.get(cur)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(new Node(i, cnt + 1));
                }
            }
        }

        return -1;
    }

}

class Node {
    int idx, cnt;

    public Node(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
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