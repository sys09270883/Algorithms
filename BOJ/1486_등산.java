import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = 987654321;
    static int N, M, T, D;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static List<List<Node>> adj;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();   T = io.nextInt();   D = io.nextInt();
        map = new int[N][M];
        adj = new ArrayList<>(N * M);
        for (int i = 0; i < N * M; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String s = io.next();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if ('A' <= c && c <= 'Z')
                    map[i][j] = c - 'A';
                else if ('a' <= c && c <= 'z')
                    map[i][j] = c - 'a' + 26;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    int cur = i * M + j;
                    int next = ni * M + nj;
                    if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                        continue;
                    int diff = Math.abs(map[i][j] - map[ni][nj]);
                    if (Math.abs(map[i][j] - map[ni][nj]) > T)
                        continue;
                    if (map[i][j] >= map[ni][nj])
                        adj.get(cur).add(new Node(next, 1));
                    else
                        adj.get(cur).add(new Node(next, diff * diff));
                }
            }
        }

        int ans = map[0][0];
        int[] dists = dijkstra(0);
        for (int i = 1; i < N * M; i++) {
            int[] rdists = dijkstra(i);
            if (rdists[0] == INF || dists[i] == INF)
                continue;
            if (rdists[0] + dists[i] <= D)
                ans = Math.max(ans, map[i / M][i % M]);
        }

        io.write(ans);
    }

    private static int[] dijkstra(int s) {
        int[] dists = new int[N * M];
        Arrays.fill(dists, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, dists[s] = 0));

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.idx;
            int dist = tmp.dist;

            if (dist > dists[cur])
                continue;

            for (Node n : adj.get(cur)) {
                int next = n.idx;
                int ndist = + n.dist;
                if (dists[next] > dists[cur] + ndist)
                    pq.add(new Node(next, dists[next] = dists[cur] + ndist));
            }
        }

        return dists;
    }
}

class Node implements Comparable<Node> {
    int idx, dist;

    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node node) {
        return this.dist - node.dist;
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
        bw.write(sb.toString());
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