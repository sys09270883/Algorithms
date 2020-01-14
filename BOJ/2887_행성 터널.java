import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[] parent;

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), res = 0;
        parent = new int[N + 1];
        Arrays.setAll(parent, i -> i);
        List<Point> points = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(new Point(io.nextInt(), io.nextInt(), io.nextInt(), i));
        }

        Collections.sort(points, Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N - 1; i++) {
            int dx = Math.abs(points.get(i).x - points.get(i + 1).x);
            edges.add(new Edge(points.get(i).idx, points.get(i + 1).idx, dx));
        }

        Collections.sort(points, Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < N - 1; i++) {
            int dy = Math.abs(points.get(i).y - points.get(i + 1).y);
            edges.add(new Edge(points.get(i).idx, points.get(i + 1).idx, dy));
        }

        Collections.sort(points, Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < N - 1; i++) {
            int dz = Math.abs(points.get(i).z - points.get(i + 1).z);
            edges.add(new Edge(points.get(i).idx, points.get(i + 1).idx, dz));
        }

        Collections.sort(edges, Comparator.comparingInt(p -> p.d));

        for (Edge e : edges) {
            if (find(e.p1) != find(e.p2)) {
                union(e.p1, e.p2);
                res += e.d;
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

class Point {
    int x, y, z, idx;

    public Point(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}

class Edge {
    int p1, p2, d;

    public Edge(int p1, int p2, int d) {
        this.p1 = p1;
        this.p2 = p2;
        this.d = d;
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