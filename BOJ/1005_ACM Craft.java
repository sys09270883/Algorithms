import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int n, k;
    static int[] d;
    static StringBuilder res = new StringBuilder();

    public static void main(String... args) throws IOException {
        int t = io.nextInt();

        while (t-- > 0) {
            n = io.nextInt();
            k = io.nextInt();
            d = new int[n + 1];

            List<List<Integer>> adj = new ArrayList<>(n + 1);
            for (int i = 0; i < n + 1; i++) {
                adj.add(new ArrayList<>());
            }
            int[] indegree = new int[n + 1];

            for (int i = 1; i < n + 1; i++) {
                d[i] = io.nextInt();
            }
            for (int i = 0; i < k; i++) {
                int x = io.nextInt();
                int y = io.nextInt();
                adj.get(x).add(y);
                indegree[y]++;
            }

            int w = io.nextInt();

            topologicalSort(indegree, adj, w);
        }

        io.write(res);
    }

    private static void topologicalSort(int[] indegree, List<List<Integer>> adj, int w) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] result = new int[n+1];

        for(int i = 1; i <= n; i++) {
            result[i] = d[i];

            if(indegree[i] == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(Integer i : adj.get(node)) {
                result[i] = Math.max(result[i], result[node] + d[i]);
                indegree[i]--;

                if(indegree[i] == 0)
                    queue.add(i);
            }
        }

        res.append(result[w]).append('\n');
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

    void write(char[] str) throws IOException {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            bw.write(str[i]);
        }
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}