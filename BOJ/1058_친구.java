import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static List<List<Integer>> adj;
    static boolean[] visited;
    static int N, res;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];
        String input = null;
        for (int i = 1; i < N + 1; i++) {
            input = io.next();
            for (int j = 1; j < N + 1; j++) {
                if (input.charAt(j - 1) == 'N')
                    continue;
                adj.get(i).add(j);
                adj.get(j).add(i);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (!adj.get(i).isEmpty())
                res = Math.max(res, BFS(i));
        }

        io.write(res);
    }

    private static int BFS(int idx) {
        Arrays.fill(visited, false);
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(idx, 0));
        visited[idx] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int step = tmp.step;

            if (step >= 2)
                continue;

            for (Integer next : adj.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new Node(next, step + 1));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}

class Node {
    int idx, step;

    public Node(int idx, int step) {
        this.idx = idx;
        this.step = step;
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

    void write(char c) throws IOException {
        bw.write(c);
        close();
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