import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int n, m;
    static int[][] maze;
    static boolean[][][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String... args) throws IOException {
        n = io.nextInt();
        m = io.nextInt();
        maze = new int[n][n];
        visited = new boolean[n][n][m + 1][2];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = io.nextInt();
            }
        }

        int cnt = BFS();

        if (cnt > -1) {
            int date = (int)Math.ceil((double)cnt / (m * 2));
            int day = (int)Math.ceil((double)cnt / m);
            res.append(date).append(' ').append(day % 2 == 1 ? "sun" : "moon");
        }
        else
            res.append(-1);
        io.write(res);
    }

    private static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 1));
        visited[0][0][1][1] = true;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;
            int cnt = tmp.cnt;
            int day = tmp.day;

            if (x == n - 1 && y == n - 1)
                return cnt;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nCnt = (cnt + 1) % m;
                int nDay = (int)Math.ceil((double)(cnt + 1) / m) % 2 == 1 ? 1 : 0;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny][nCnt][nDay])
                    continue;

                if (maze[nx][ny] == 1) {
                    if (day == 1)
                        continue;

                    while (true) {
                        nx += dx[i];
                        ny += dy[i];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny][nCnt][nDay])
                            break;

                        else if (maze[nx][ny] == 0) {
                            visited[nx][ny][nCnt][nDay] = true;
                            queue.add(new Node(nx, ny, cnt + 1, nDay));
                            break;
                        }
                    }
                }
                else {
                    visited[nx][ny][nCnt][nDay] = true;
                    queue.add(new Node(nx, ny, cnt + 1, nDay));
                }
            }
        }

        return -1;
    }
}

class Node {
    int x, y, cnt, day;

    public Node(int x, int y, int cnt, int day) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.day = day;
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