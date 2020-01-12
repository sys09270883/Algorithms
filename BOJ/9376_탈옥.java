import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int esc = 0b11;
    static int h, w, ans;
    static char[][] map;
    static int[][][] visited;
    static List<Node> start;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();
        while (T-- > 0) {
            h = io.nextInt();   w = io.nextInt();     ans = Integer.MAX_VALUE;
            map = new char[h + 2][w + 2];
            visited = new int[h + 2][w + 2][3];
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        visited[i][j][k] = -1;
                    }
                }
            }
            start = new ArrayList<>();
            start.add(new Node(0, 0));
            for (char[] row : map) {
                Arrays.fill(row, '.');
            }
            for (int i = 1; i < h + 1; i++) {
                String input = io.next();
                for (int j = 1; j < w + 1; j++) {
                    map[i][j] = input.charAt(j - 1);
                    if (map[i][j] == '$') {
                        map[i][j] = '.';
                        start.add(new Node(i, j));
                    }
                }
            }

            BFS();

            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (map[i][j] == '*')
                        continue;
                    int k = visited[i][j][0] + visited[i][j][1] + visited[i][j][2];
                    if (map[i][j] == '#')
                        k -= 2;
                    ans = Math.min(ans, k);
                }
            }

            res.append(ans).append('\n');
        }

        io.write(res);
    }

    private static void BFS() {
        for (int k = 0; k < 3; k++) {
            Deque<Node> dq = new LinkedList<>();
            Node tmp = start.get(k);
            int sx = tmp.x, sy = tmp.y;
            dq.add(new Node(sx, sy));
            visited[sx][sy][k] = 0;

            while (!dq.isEmpty()) {
                tmp = dq.poll();
                int x = tmp.x;
                int y = tmp.y;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2)
                        continue;
                    if (visited[nx][ny][k] >= 0 || map[nx][ny] == '*')
                        continue;
                    if (map[nx][ny] == '.') {
                        visited[nx][ny][k] = visited[x][y][k];
                        dq.addFirst(new Node(nx, ny));
                    }
                    else if (map[nx][ny] == '#') {
                        visited[nx][ny][k] = visited[x][y][k] + 1;
                        dq.addLast(new Node(nx, ny));
                    }
                }
            }
        }
    }

    private static void print() {
        for (int i = 0; i < h + 2; i++) {
            for (int j = 0; j < w + 2; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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