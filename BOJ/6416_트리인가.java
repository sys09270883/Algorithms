import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static Map<Integer, List<Integer>> adj;
    static Map<Integer, Integer> indegree;
    static Map<Integer, Boolean> visited;

    public static void main(String... args) throws IOException {
        int u, v, k = 1;
        StringBuilder res = new StringBuilder();
        adj = new HashMap<>();
        indegree = new HashMap<>();
        visited = new HashMap<>();
        while (true) {
            u = io.nextInt();   v = io.nextInt();
            if (u == 0 && v == 0) {
                res.append("Case ").append(k++).append(" is ").append(adj.isEmpty() ? "" : BFS() ? "" : "not ").append("a tree.\n");
                adj.clear();
                indegree.clear();
                continue;
            }
            else if (u < 0 && v < 0)
                break;
            if (!adj.containsKey(u))
                adj.put(u, new ArrayList<>());
            adj.get(u).add(v);
            if (!indegree.containsKey(u))
                indegree.put(u, 0);
            if (!indegree.containsKey(v))
                indegree.put(v, 0);
            indegree.put(v, indegree.get(v) + 1);
            visited.put(u, false);
            visited.put(v, false);
        }
        io.write(res);
    }

    private static boolean BFS() {
        Queue<Integer> queue = new LinkedList<>();
        for (Integer key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.add(key);
                visited.put(key, true);
            }
        }
        if (queue.size() != 1)
            return false;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (adj.get(cur) == null)
                continue;
            for (Integer next : adj.get(cur)) {
                if (visited.get(next))
                    return false;
                visited.put(next, true);
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