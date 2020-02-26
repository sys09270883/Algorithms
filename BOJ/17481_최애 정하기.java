import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, K, max;
    static int[] A, B;
    static boolean[] vis;
    static List<List<Integer>> adj;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        Map<String, Integer> hm = new HashMap<>();
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        int sz = Math.max(N, M);
        A = new int[sz]; B = new int[sz];
        vis = new boolean[sz];
        Arrays.fill(A, -1);
        Arrays.fill(B, -1);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            hm.put(io.next(), i);
        }
        for (int i = 0; i < N; i++) {
            K = io.nextInt();
            for (int j = 0; j < K; j++) {
                adj.get(i).add(hm.get(io.next()));
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(vis, false);
            if (DFS(i))
                max++;
        }
        if (max >= N)
            res.append("YES\n");
        else
            res.append("NO\n").append(max).append('\n');
        io.write(res);
    }

    private static boolean DFS(int cur) {
        vis[cur] = true;
        for (Integer next : adj.get(cur)) {
            if (B[next] == -1 || !vis[B[next]] && DFS(B[next])) {
                A[cur] = next;
                B[next] = cur;
                return true;
            }
        }
        return false;
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