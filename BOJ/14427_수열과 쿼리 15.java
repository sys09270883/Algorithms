import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        Set<Node> ts = new TreeSet<>();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
            ts.add(new Node(i, arr[i]));
        }
        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            if (io.nextInt() == 1) {
                int a = io.nextInt(), b = io.nextInt();
                ts.remove(new Node(a, arr[a]));
                arr[a] = b;
                ts.add(new Node(a, arr[a]));
            }
            else
                res.append(ts.iterator().next().idx).append('\n');
        }
        io.write(res);
    }

}

class Node implements Comparable<Node> {
    int idx, val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node node) {
        if (this.val == node.val)
            return this.idx - node.idx;
        return this.val - node.val;
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