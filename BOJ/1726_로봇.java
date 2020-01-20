import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, sx, sy, sd, ex, ey, ed;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = io.nextInt();
            }
        }

        visited = new boolean[N][M][5];
        sx = io.nextInt() - 1;  sy = io.nextInt() - 1;  sd = io.nextInt();
        ex = io.nextInt() - 1;  ey = io.nextInt() - 1;  ed = io.nextInt();

        io.write(BFS());
    }

    private static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, sd, 0));
        visited[sx][sy][sd] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;
            int d = tmp.dir;
            int cnt = tmp.cnt;

            if (x == ex && y == ey && d == ed)
                return cnt;

            // go
            for (int i = 1; i < 4; i++) {
                int[] next = go(x, y, i, d);
                int nx = next[0], ny = next[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if (visited[nx][ny][d] || map[nx][ny] == 1)
                    continue;

                visited[nx][ny][d] = true;
                queue.add(new Node(nx, ny, d, cnt + 1));
            }

            // turn
            for (int i = 0; i < 2; i++) {
                int nd = turn(d, i);

                if (visited[x][y][nd])
                    continue;

                visited[x][y][nd] = true;
                queue.add(new Node(x, y, nd, cnt + 1));
            }
        }
        return -1;
    }

    private static int turn(int dir, int n) {
        if (n == 0) {
            if (dir == 1)
                return 4;
            else if (dir == 2)
                return 3;
            else if (dir == 3)
                return 1;
            else
                return 2;
        }
        else {
            if (dir == 1)
                return 3;
            else if (dir == 2)
                return 4;
            else if (dir == 3)
                return 2;
            else if (dir == 4)
                return 1;
        }
        return dir;
    }

    private static int[] go(int x, int y, int k, int dir) {
        int[] next = new int[2];

        if (dir == 1) {
            for (int i = 1; i < k + 1; i++) {
                if (y + i < M && map[x][y + i] == 1) {
                    next[0] = -1;
                    next[1] = -1;
                    return next;
                }
            }
            next[0] = x;
            next[1] = y + k;
        }
        else if (dir == 2) {
            for (int i = 1; i < k + 1; i++) {
                if (y - i >= 0 && map[x][y - i] == 1) {
                    next[0] = -1;
                    next[1] = -1;
                    return next;
                }
            }
            next[0] = x;
            next[1] = y - k;
        }
        else if (dir == 3) {
            for (int i = 1; i < k + 1; i++) {
                if (x + i < N && map[x + i][y] == 1) {
                    next[0] = -1;
                    next[1] = -1;
                    return next;
                }
            }
            next[0] = x + k;
            next[1] = y;
        }
        else if (dir == 4) {
            for (int i = 1; i < k + 1; i++) {
                if (x - i >= 0 && map[x - i][y] == 1) {
                    next[0] = -1;
                    next[1] = -1;
                    return next;
                }
            }
            next[0] = x - k;
            next[1] = y;
        }

        return next;
    }
}

class Node {
    int x, y, dir, cnt;

    public Node(int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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