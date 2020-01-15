import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, n1, n2, res;
    static List<Edge> edges;
    static int[] parent;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        parent = new int[N + 1];
        Arrays.setAll(parent, i -> i);
        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            edges.add(new Edge(io.nextInt(), io.nextInt(), io.nextInt()));
        }

        Collections.sort(edges, Comparator.comparingInt(e -> e.cost));

        for (int i = 0, cnt = 0; cnt < N - 2; i++) {
            n1 = edges.get(i).n1;
            n2 = edges.get(i).n2;
            if (find(n1) != find(n2)) {
                union(n1, n2);
                cnt++;
                res += edges.get(i).cost;
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
        x = parent[x];
        y = parent[y];
        if (x != y)
            parent[y] = x;
    }
}

class Edge {
    int n1, n2, cost;

    public Edge(int n1, int n2, int cost) {
        this.n1 = n1;
        this.n2 = n2;
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