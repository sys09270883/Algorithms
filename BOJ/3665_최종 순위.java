import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[] arr, indegree, ans;
    static boolean[][] adj;
    static int n, m;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            n = io.nextInt();
            arr = new int[n + 1];
            indegree = new int[n + 1];
            ans = new int[n + 1];
            adj = new boolean[n + 1][n + 1];
            for (int i = 1; i < n + 1; i++) {
                arr[i] = io.nextInt();
            }
            for (int i = 1; i < n + 1; i++) {
                for (int j = i + 1; j < n + 1; j++) {
                    adj[arr[i]][arr[j]] = true;
                    indegree[arr[j]]++;
                }
            }
            m = io.nextInt();
            for (int i = 0; i < m; i++) {
                int a = io.nextInt(), b = io.nextInt();
                if (adj[a][b]) {
                    a ^= b;
                    b ^= a;
                    a ^= b;
                }
                adj[a][b] = true;
                adj[b][a] = false;
                indegree[a]--;
                indegree[b]++;
            }

            int flag = topologicalSort();
            if (flag == 1)
                res.append("IMPOSSIBLE\n");
            else if (flag == 2)
                res.append("?\n");
            else {
                for (int i = 1; i < n + 1; i++) {
                    res.append(ans[i]).append(' ');
                }
                res.append('\n');
            }
        }

        io.write(res);
    }

    private static int topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        for (int i = 1; i < n + 1; i++) {
            if (queue.isEmpty())
                return 1;
            if (queue.size() >= 2)
                return 2;

            int cur = queue.poll();
            ans[i] = cur;

            for (int j = 1; j < n + 1; j++) {
                if (adj[cur][j] && --indegree[j] == 0)
                    queue.add(j);
            }
        }

        return 3;
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