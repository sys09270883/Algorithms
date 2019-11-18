/*
https://www.acmicpc.net/problem/16568
[문제]
한길이는 수습 마법사이며, 마법사의 영혼을 받기 위해 줄을 서있다. 한길이는 강력한 힘을 얻기 위해 인성을 버렸다. 
그리고 최고로 강력한 엔비스카의 영혼을 받기 위해서 새치기를 하기로 결심했다.

한길이의 앞에 N명의 사람들이 줄 서있다. 1초가 지날 때마다 줄의 맨 앞 사람은 영혼을 받고 집에 간다. 그리고 1초마다 한길이는 다음과 같은 행동을 할 수 있다.

기다리기
a명 앞으로 가기 (앞에 최소 a명 있을 때)
b명 앞으로 가기 (앞에 최소 b명 있을 때)
단, 한길이는 새치기에는 도가 텄기때문에, 모든 행동을 0초만에 할 수 있다.

예를 들어 N = 5, a = 1, b = 2라고 하자. 5초동안 기다리기만 하면 줄의 맨 앞 사람이 나가기 때문에 줄의 맨 앞에 서있기까지 5초가 걸린다. 
하지만 맨 앞 한 명이 집에 가고 한길이가 2명 앞으로 새치기, 그 다음 한 명이 집에 가고 1명 앞으로 새치기하면 2초만에 줄의 맨 앞에 서게 된다. 
유의할 점은 1초에 맨 앞 한 명이 가고 2명 앞으로 새치기하고 맨 앞 한 명이 가면 1명이 남는다. 이 때 2명 앞으로 새치기는 불가능하다.

한길이가 줄의 맨 앞에 서있으려면 최소 몇 초가 걸리는가?

[입력]
첫째 줄에 N, a, b가 주어진다. (0 ≤ N ≤ 1,000,000, 0 ≤ a, b ≤ N)

[출력]
한길이가 맨 앞에 서는데 걸리는 최소 시간을 출력한다.

[풀이]
N에서 0으로 BFS탐색을 한다.
 + DP로도 풀이가 가능하다.
 
*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, jumpA, jumpB;
    static int[] arr;
    static boolean[] visited;
    static int[] dx = new int[3];

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        jumpA = io.nextInt();
        jumpB = io.nextInt();
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        dx[0] = 1;
        dx[1] = 1 + jumpA;
        dx[2] = 1 + jumpB;

        io.write(BFS());
    }

    private static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int cur = tmp.idx;
            int cnt = tmp.cnt;

            if (cur == 0)
                return cnt;

            for (int i = 0; i < 3; i++) {
                int next = cur - dx[i];

                if (next < 0 || visited[next])
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
        super();
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
