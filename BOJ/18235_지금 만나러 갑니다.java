import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, A, B;
    static int[] visitedA, visitedB;
    static int[] dx = {-1, 1};

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        A = io.nextInt();
        B = io.nextInt();
        visitedA = new int[N + 1];
        visitedB = new int[N + 1];
        Arrays.fill(visitedA, -1);
        Arrays.fill(visitedB, -1);
        io.write(BFS());
    }

    private static int BFS() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(A, 0, true));
        pq.add(new Node(B, 0, false));
        visitedA[A] = 0;
        visitedB[B] = 0;

        while (!pq.isEmpty()) {
            Node tmp = pq.poll();
            int cur = tmp.idx;
            int cnt = tmp.cnt;
            boolean flag = tmp.flag;

            if (visitedA[cur] == visitedB[cur] && visitedB[cur] > -1)
                return visitedB[cur];
 
            for (int i = 0; i < 2; i++) {
                int next = cur + dx[i] * (1 << cnt);

                if (next < 1 || next > N)
                    continue;

                if (flag)
                    visitedA[next] = cnt + 1;
                else
                    visitedB[next] = cnt + 1;
                pq.add(new Node(next, cnt + 1, flag));
            }
        }

        return -1;
    }

}

class Node implements Comparable<Node>{
    int idx, cnt;
    boolean flag;

    public Node(int idx, int cnt, boolean flag) {
        this.idx = idx;
        this.cnt = cnt;
        this.flag = flag;
    }

    @Override
    public int compareTo(Node o) {
        return this.cnt - o.cnt;
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
