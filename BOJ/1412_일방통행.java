import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static char[][] arr;
    static List<List<Integer>> adj;
    static int[] indegree;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new char[N + 1][N + 1];
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        indegree = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            String in = io.next();
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = in.charAt(j - 1);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (arr[i][j] == 'Y') {
                    if (i == j) {
                        io.write("NO");
                        return;
                    }
                    if (arr[j][i] == 'N') {
                        adj.get(i).add(j);
                        indegree[j]++;
                    }
                }
            }
        }

        topologicalSort();

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] > 0) {
                io.write("NO");
                return;
            }
        }

        io.write("YES");
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : adj.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0)
                    queue.add(next);
            }
        }
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