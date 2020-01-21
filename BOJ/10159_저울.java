import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M, cnt;
    static boolean[][] arr;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        arr = new boolean[N + 1][N + 1];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int a = io.nextInt(), b = io.nextInt();
            arr[a][b] = true;
        }

        floyd();

        for (int i = 1; i < N + 1; i++) {
            cnt = -1;
            for (int j = 1; j < N + 1; j++) {
                if (!arr[i][j] && !arr[j][i])
                    cnt++;
            }
            res.append(cnt).append('\n');
        }

        io.write(res);
    }

    private static void floyd() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (arr[i][k] && arr[k][j])
                        arr[i][j] = true;
                }
            }
        }
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