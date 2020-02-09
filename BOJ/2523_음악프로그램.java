import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static List<List<Integer>> adj;
    static int[] indegree, arr;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        indegree = new int[N + 1];
        arr = new int[N + 1];
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int size = io.nextInt();
            if (size == 0)
                continue;

            int prev = io.nextInt();
            for (int j = 1; j < size; j++) {
                int cur = io.nextInt();
                indegree[cur]++;
                adj.get(prev).add(cur);
                prev = cur;
            }
        }

        if (topologicalSort()) {
            for (int i = 1; i < N + 1; i++) {
                res.append(arr[i]).append('\n');
            }
            io.write(res);
        }
        else
            io.write("0");
    }

    private static boolean topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        for (int i = 1; i < N + 1; i++) {
            if (queue.isEmpty())
                return false;

            int cur = queue.poll();
            arr[i] = cur;
            for (Integer next : adj.get(cur)) {
                if (--indegree[next] == 0)
                    queue.add(next);
            }
        }

        return true;
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