import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[][] left, right;
    static boolean[][] map;
    static int N, M, L, R, sx, sy, ans;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        L = io.nextInt();
        R = io.nextInt();
        map = new boolean[N][M];
        left = new int[N][M];
        right = new int[N][M];
        String line = null;
        for (int i = 0; i < N; i++) {
            line = io.nextLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == '1')
                    map[i][j] = true;
                else if (c == '2') {
                    sx = i;
                    sy = j;
                }
            }
        }

        DFS(sx, sy, 0, 0);

        io.write(ans);
    }

    private static void DFS(int x, int y, int l, int r) {
        if (!map[x][y]) {
            map[x][y] = true;
            ans++;
        }

        left[x][y] = l;
        right[x][y] = r;

        if (x - 1 >= 0 && (!map[x - 1][y] || left[x - 1][y] > l || right[x - 1][y] > r))
            DFS(x - 1, y, l, r);
        if (x + 1 < N && (!map[x + 1][y] || left[x + 1][y] > l || right[x + 1][y] > r))
            DFS(x + 1, y, l, r);
        if (y - 1 >= 0 && l < L && (!map[x][y - 1] || left[x][y - 1] > l || right[x][y - 1] > r))
            DFS(x, y - 1, l + 1, r);
        if (y + 1 < M && r < R && (!map[x][y + 1] || left[x][y + 1] > l || right[x][y + 1] > r))
            DFS(x, y + 1, l, r + 1);
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