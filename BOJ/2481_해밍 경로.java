import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, K, M;
    static int[] arr;
    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] trace;
    static Map<Integer, Integer> exist;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   K = io.nextInt();
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        exist = new HashMap<>();
        trace = new int[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(io.next(), 2);
            exist.put(arr[i], i);
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < K; j++) {
                int next = arr[i] + ((arr[i] & (1 << j)) == 0 ? 1 << j : -(1 << j));
                if (exist.containsKey(next))
                    adj.get(i).add(exist.get(next));
            }
        }

        BFS();

        M = io.nextInt();
        for (int i = 0; i < M; i++) {
            int t = io.nextInt();
            if (visited[t]) {
                Stack<Integer> stack = new Stack<>();
                int tmp = t;
                while (tmp != 1) {
                    stack.add(tmp);
                    tmp = trace[tmp];
                }
                stack.add(1);
                while (!stack.isEmpty()) {
                    res.append(stack.pop()).append(' ');
                }
                res.append('\n');
            }
            else
                res.append("-1\n");
        }

        io.write(res);
    }

    private static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : adj.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    trace[next] = cur;
                    queue.add(next);
                }
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