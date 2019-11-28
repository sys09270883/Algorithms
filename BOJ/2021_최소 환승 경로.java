/*
https://www.acmicpc.net/problem/2021
[문제]
어떤 도시의 지하철 노선에 대한 정보가 주어졌을 때, 출발지에서 목적지까지의 최소 환승 경로를 구하는 프로그램을 작성하시오.
실제 경로를 구할 필요는 없고, 환승 회수만을 구하면 된다.

[입력]
첫째 줄에 역의 개수 N(1≤N≤100,000), 노선의 개수 L(1≤L≤100,000)이 주어진다.
다음 L개의 줄에는 각 노선이 지나는 역이 순서대로 주어지며 각 줄의 마지막에는 -1이 주어진다.
마지막 줄에는 출발지 역의 번호와 목적지 역의 번호가 주어진다. 역 번호는 1부터 N까지의 정수로 표현된다.
각 노선의 길이의 합은 1,000,000을 넘지 않는다.

[출력]
첫째 줄에 최소 환승 회수를 출력한다. 불가능한 경우에는 -1을 출력한다.

[풀이]
환승역 또한 정점으로 취급하여 인접 리스트에 추가한다.
BFS를 하면서 (이동한 거리 / 2 - 1)이 환승역의 개수이다.
시작점과 도착점이 같은 경우 예외처리 해주어야 한다. (S == E)

 */
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, L, st, S, E, ans;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        L = io.nextInt();
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + L + 1; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + L + 1];

        for (int i = N + 1; i < N + L + 1; i++) {
            while ((st = io.nextInt()) != -1) {
                adj.get(i).add(st);
                adj.get(st).add(i);
            }
        }

        ans = BFS(io.nextInt(), io.nextInt());
        if (ans < 0)
            io.write(-1);
        else if (ans == 0)
            io.write(0);
        else
            io.write(ans / 2 - 1);
    }

    private static int BFS(int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int cnt = tmp.cnt;

            if (cur == end)
                return cnt;

            for (int next : adj.get(cur)) {
                if (visited[next])
                    continue;

                visited[next] = true;
                queue.add(new Node(next, cnt + 1));
            }
        }

        return -1;
    }
}

class Node {
    int idx, cnt;

    public Node(int idx, int cnt) {
        this.idx = idx;
        this.cnt = cnt;
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

    void close() throws IOException {
        bw.close();
        br.close();
    }
}