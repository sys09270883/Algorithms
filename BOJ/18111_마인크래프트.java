import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), M = io.nextInt(), B = io.nextInt(), t = Integer.MAX_VALUE, h = 0;
        int low = 500, high = 0;
        int[][] arr = new int[N][M];
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = io.nextInt();
                low = Math.min(low, arr[i][j]);
                high = Math.max(high, arr[i][j]);
            }
        }

        for (int i = low; i <= high; i++) {
            int time = 0;
            int b = B;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    int diff = Math.abs(arr[j][k] - i);
                    if (arr[j][k] > i) {
                        time += 2 * diff;
                        b += diff;
                    }
                    else if (arr[j][k] < i) {
                        time += diff;
                        b -= diff;
                    }
                }
            }

            if (b >= 0) {
                if (time <= t) {
                    t = time;
                    h = i;
                }
            }
        }

        res.append(t).append(' ').append(h);
        io.write(res);
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