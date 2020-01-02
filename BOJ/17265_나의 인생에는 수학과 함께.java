import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE, N;
    static char[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = io.next().charAt(0);
            }
        }
        StringBuilder res = new StringBuilder();

        DFS(0, 0, map[0][0] - '0');

        res.append(MAX).append(' ').append(MIN);
        io.write(res);
    }

    private static void DFS(int x, int y, int s) {
        if (x == N - 1 && y == N - 1) {
            MAX = Math.max(MAX, s);
            MIN = Math.min(MIN, s);
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= N || ny >= N)
                continue;

            if (isOperator(x, y))
                DFS(nx, ny, calc(s, map[x][y], map[nx][ny] - '0'));
            else
                DFS(nx, ny, s);
        }
    }

    private static boolean isOperator(int x, int y) {
        if (map[x][y] == '+' || map[x][y] == '*' || map[x][y] == '-')
            return true;
        return false;
    }

    private static int calc(int a, char op, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }

        return -1;
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