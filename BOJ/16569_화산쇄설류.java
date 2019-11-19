/*
https://www.acmicpc.net/problem/16569
[문제]
화산학자 윤재상은 어느 화산섬을 탐사하러 갔다가 곧 섬에 있는 화산들이 곧 폭발하기 시작할 것이라는 급보와 각 화산의 폭발 시점 정보를 받았다.

섬은 M행 N열의 행렬로 표현된다. 어떤 화산의 위치를 (x, y), 폭발을 시작한 시각을 t 라고 하자. 
t+δ 시각이 되면 δ ≥ |u-x|+|v-y|인 모든 (u, v)위치의 지대들은 높이 무관하게 화산쇄설류가 덮치게 된다. 재상인 빨리 탈출을 하고싶다.

재상이는 처음에 X행 Y열에 있다.
재상이는 단위 시간 당 상하좌우 한 칸만 움직일 수 있다.
재상이는 화산이 있는 위치와 화산쇄설류가 뒤덮인 곳은 갈 수 없다.
재상이는 화산쇄설류를 피해서 되도록 가장 높은 곳으로 피하고 싶고, 되도록 가장 빨리 도달하기를 원한다. 
재상이가 화산쇄설류를 피해서 도달할 수 있는 가장 높은 고도와, 그 고도까지 도달하는데 걸리는 최소 시간을 구한다.

[입력]
첫 번째 줄에 정수 M, N, V이 공백으로 구분되어 주어진다. (1 ≤ M, N ≤ 100, 1 ≤ V ≤ min(5,000, M×N))

그 다음 줄에 X, Y가 공백으로 구분되어 주어진다. (1 ≤ X ≤ M, 1 ≤ Y ≤ N)

그 다음 줄부터 M개의 줄마다 N개의 공백으로 구분된 수들이 주어진다. i행 j열의 값은 (i, j) 지대의 고도 hij 를 나타낸다. (0 ≤ hij ≤ 10,000)

그 다음 줄부터 V개의 줄이 주어진다. i번째 줄에 xi, yi, ti가 공백으로 구분되어 주어진다. 이 수들은 i번째 화산의 위치 (xi, yi,)와 화산의 분출시각 ti를 의미한다. (1 ≤ xi ≤ M, 1 ≤ yi ≤ N, 0 ≤ ti ≤ 200)

위치, 시간, 고도 수치들은 모두 정수이다. X행 Y열에 화산이 있는 입력은 주어지지 않는다.

[출력]
재상이가 도달할 수 있는 최고 높이와 그 높이에 도달할 수 있는 최단 시간을 공백을 구분하여 출력한다.

[풀이]
2차원 배열에 화산이 폭발하는 시간을 기록한다.
  i. 이 때, 아직 터지지 않은 화산을 미리 처리할 경우 그보다 먼저 터지는 화산이 갱신을 하지 못하므로 먼저 터지는 화산 순으로 화산에 대한 BFS를 해야 한다.
  ii. 따라서 화산들을 시간에 대한 최소 우선순위 큐에 넣고 뽑아 내면서 BFS를 진행한다.
화산의 폭발 시간을 기록한 2차원 배열을 기준으로 재상이에 대한 BFS를 진행한다.
  i. 이동한 칸이 최고 고도일 경우, maxHeight과 totalTime을 갱신한다.

 + 큐에 넣어줄 때 반복적인 값을 넣어줘서 메모리 초과가 발생했다.
 + 1-1번 사항을 고려하지 못했다.
 + 화산을 지나가지 못하게 해야한다.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int M, N, V, X, Y, maxHeight, totalTime;
    static int[][] map;
    static boolean[][] visited;
    static int[][] ashMap;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String... args) throws IOException {
        M = io.nextInt();
        N = io.nextInt();
        V = io.nextInt();
        X = io.nextInt();
        Y = io.nextInt();
        map = new int[M + 1][N + 1];
        visited = new boolean[M + 1][N + 1];
        ashMap = new int[M + 1][N + 1];
        for (int[] row : ashMap) {
            Arrays.fill(row, -1);
        }
        StringBuilder res = new StringBuilder();
        PriorityQueue<Node> volcanoes = new PriorityQueue<Node>();

        for (int i = 1; i < M + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = io.nextInt();
            }
        }

        for (int i = 0; i < V; i++) {
            volcanoes.add(new Node(io.nextInt(), io.nextInt(), io.nextInt()));
        }

        for (int i = 0; i < V; i++) {
            Node tmp = volcanoes.poll();
            ashBFS(tmp.x, tmp.y, tmp.time);
        }

        BFS();
        res.append(maxHeight).append(' ').append(totalTime);
        io.write(res);
    }

    private static void ashBFS(int r, int c, int t) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(r, c, t));
        ashMap[r][c] = 0;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;
            int time = tmp.time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nTime = time + 1;

                if (nx < 1 || ny < 1 || nx > M || ny > N)
                    continue;

                if (ashMap[nx][ny] > -1 && nTime >= ashMap[nx][ny])
                    continue;

                ashMap[nx][ny] = nTime;
                queue.add(new Node(nx, ny, nTime));
            }
        }
    }

    private static void BFS() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(X, Y, 0));
        visited[X][Y] = true;
        maxHeight = map[X][Y];
        totalTime = 0;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;
            int time = tmp.time;

            if (maxHeight < map[x][y]) {
                maxHeight = map[x][y];
                totalTime = time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nTime = time + 1;

                if (nx < 1 || ny < 1 || nx > M || ny > N)
                    continue;

                if (visited[nx][ny] || nTime >= ashMap[nx][ny])
                    continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, nTime));
            }
        }
    }

}

class Node implements Comparable<Node>{
    int x, y, time;

    public Node(int x, int y, int time) {
        super();
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return this.time - o.time;
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