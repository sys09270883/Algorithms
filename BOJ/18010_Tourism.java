import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int n, m, s, ans;
    static List<List<Integer>> adj;
    static int[] arr, indegree, val;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < T + 1; i++) {
            n = io.nextInt();   m = io.nextInt();
            ans = 0;
            arr = new int[n + 1];
            indegree = new int[n + 1];
            visited = new boolean[n + 1];
            val = new int[n + 1];
            adj = new ArrayList<>(n + 1);
            for (int j = 0; j < n + 1; j++) {
                adj.add(new ArrayList<>());
            }

            for (int j = 1; j < n + 1; j++) {
                arr[j] = io.nextInt();
            }

            for (int j = 0; j < m; j++) {
                int x = io.nextInt(), y = io.nextInt();
                adj.get(x).add(y);
                adj.get(y).add(x);
                indegree[x]++;
                indegree[y]++;
            }
            s = io.nextInt();
            res.append("Data Set ").append(i).append(":\n").append(topologicalSort()).append("\n\n");
        }

        io.write(res);
    }

    private static int topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (i != s && indegree[i] == 1) {
                visited[i] = true;
                queue.add(i);
            }
        }
        int ans1 = 0, ans2 = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : adj.get(cur)) {
                if (visited[next])
                    continue;
                indegree[next]--;
                val[next] = Math.max(val[next], val[cur] + arr[cur]);
                if (indegree[next] == 1 && next != s) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (visited[i])
                continue;
            ans1 += arr[i];
            ans2 = Math.max(ans2, val[i]);
        }
        return ans1 + ans2;
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