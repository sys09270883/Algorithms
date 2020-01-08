import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static List<List<Node>> adj;
    static int[] costs, company;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        company = new int[N + 1];
        for (int i = 0; i < N; i++) {
            company[i] = io.nextInt();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int e = io.nextInt();
                if (e == 0)
                    continue;
                adj.get(i).add(new Node(j, e, company[j], 0));
            }
        }

        io.write(dijkstra());
    }

    private static String dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, costs[0] = 0, company[0], 0));
        StringBuilder res = new StringBuilder();

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.idx;
            int curCost = tmp.cost;
            int company = tmp.company;
            int cnt = tmp.cnt;

            if (curCost > costs[cur])
                continue;

            if (cur == M) {
                res.append(cnt).append(' ').append(curCost);
                return res.toString();
            }

            for (Node n : adj.get(cur)) {
                int next = n.idx;
                int nextCost = n.cost;
                int nextCompany = n.company;

                if (costs[next] > costs[cur] + nextCost) {
                    costs[next] = costs[cur] + nextCost;
                    if (company == nextCompany)
                        pq.add(new Node(next, costs[next], nextCompany, cnt));
                    else
                        pq.add(new Node(next, costs[next], nextCompany, cnt + 1));
                }
            }
        }

        return res.toString();
    }

}

class Node implements Comparable<Node> {
    int idx, cost, company, cnt;

    public Node(int idx, int cost, int company, int cnt) {
        this.idx = idx;
        this.cost = cost;
        this.company = company;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node node) {
        if (this.cnt == node.cnt)
            return this.cost - node.cost;
        return this.cnt - node.cnt;
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
