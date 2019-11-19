/*
https://www.acmicpc.net/problem/16569
[����]
ȭ������ ������� ��� ȭ�꼶�� Ž���Ϸ� ���ٰ� �� ���� �ִ� ȭ����� �� �����ϱ� ������ ���̶�� �޺��� �� ȭ���� ���� ���� ������ �޾Ҵ�.

���� M�� N���� ��ķ� ǥ���ȴ�. � ȭ���� ��ġ�� (x, y), ������ ������ �ð��� t ��� ����. 
t+�� �ð��� �Ǹ� �� �� |u-x|+|v-y|�� ��� (u, v)��ġ�� ������� ���� �����ϰ� ȭ��⼳���� ��ġ�� �ȴ�. ����� ���� Ż���� �ϰ�ʹ�.

����̴� ó���� X�� Y���� �ִ�.
����̴� ���� �ð� �� �����¿� �� ĭ�� ������ �� �ִ�.
����̴� ȭ���� �ִ� ��ġ�� ȭ��⼳���� �ڵ��� ���� �� �� ����.
����̴� ȭ��⼳���� ���ؼ� �ǵ��� ���� ���� ������ ���ϰ� �Ͱ�, �ǵ��� ���� ���� �����ϱ⸦ ���Ѵ�. 
����̰� ȭ��⼳���� ���ؼ� ������ �� �ִ� ���� ���� ����, �� ������ �����ϴµ� �ɸ��� �ּ� �ð��� ���Ѵ�.

[�Է�]
ù ��° �ٿ� ���� M, N, V�� �������� ���еǾ� �־�����. (1 �� M, N �� 100, 1 �� V �� min(5,000, M��N))

�� ���� �ٿ� X, Y�� �������� ���еǾ� �־�����. (1 �� X �� M, 1 �� Y �� N)

�� ���� �ٺ��� M���� �ٸ��� N���� �������� ���е� ������ �־�����. i�� j���� ���� (i, j) ������ �� hij �� ��Ÿ����. (0 �� hij �� 10,000)

�� ���� �ٺ��� V���� ���� �־�����. i��° �ٿ� xi, yi, ti�� �������� ���еǾ� �־�����. �� ������ i��° ȭ���� ��ġ (xi, yi,)�� ȭ���� ����ð� ti�� �ǹ��Ѵ�. (1 �� xi �� M, 1 �� yi �� N, 0 �� ti �� 200)

��ġ, �ð�, �� ��ġ���� ��� �����̴�. X�� Y���� ȭ���� �ִ� �Է��� �־����� �ʴ´�.

[���]
����̰� ������ �� �ִ� �ְ� ���̿� �� ���̿� ������ �� �ִ� �ִ� �ð��� ������ �����Ͽ� ����Ѵ�.

[Ǯ��]
2���� �迭�� ȭ���� �����ϴ� �ð��� ����Ѵ�.
  i. �� ��, ���� ������ ���� ȭ���� �̸� ó���� ��� �׺��� ���� ������ ȭ���� ������ ���� ���ϹǷ� ���� ������ ȭ�� ������ ȭ�꿡 ���� BFS�� �ؾ� �Ѵ�.
  ii. ���� ȭ����� �ð��� ���� �ּ� �켱���� ť�� �ְ� �̾� ���鼭 BFS�� �����Ѵ�.
ȭ���� ���� �ð��� ����� 2���� �迭�� �������� ����̿� ���� BFS�� �����Ѵ�.
  i. �̵��� ĭ�� �ְ� ���� ���, maxHeight�� totalTime�� �����Ѵ�.

 + ť�� �־��� �� �ݺ����� ���� �־��༭ �޸� �ʰ��� �߻��ߴ�.
 + 1-1�� ������ ������� ���ߴ�.
 + ȭ���� �������� ���ϰ� �ؾ��Ѵ�.

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