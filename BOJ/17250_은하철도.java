import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr, parent;

    public static void main(String... args) throws IOException {
        N =  io.nextInt();  M = io.nextInt();
        arr = new int[N + 1];
        parent = new int[N + 1];
        Arrays.setAll(parent, i -> i);
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int a = io.nextInt(), b = io.nextInt();
            res.append(find(a) == find(b) ? arr[find(a)] : union(a, b)).append('\n');
        }

        io.write(res);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y)
            parent[x] = y;
        else if (x < y)
            parent[y] = x;

        arr[x] += arr[y];
        arr[y] = arr[x];
        return arr[x];
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

    void close() throws IOException {
        bw.close();
        br.close();
    }
}
