import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), flag = 0, number = 2 * N, idx;
        long corruption = 0, t = 1, total = N * (2 * N + 1);
        boolean RUN = true;
        int[][] arr = new int[2][N];
        StringBuilder res = new StringBuilder();

        for (int i = 0; N == 1 ? i < 2 : i < 4; i++) {
            idx = 0;
            switch (flag) {
                case 0:
                    while (arr[0][idx] != 0) {
                        idx++;
                    }
                    arr[0][idx] = number--;
                    flag = (flag + 1) % 4;
                    break;
                case 1:
                    while (arr[0][N - idx - 1] != 0) {
                        if (N - idx - 1 <= N / 2) {
                            arr[1][N - idx - 1] = number--;
                            RUN = false;
                            break;
                        }
                        idx++;
                    }
                    if (!RUN)
                        break;
                    arr[0][N - idx - 1] = number--;
                    flag = (flag + 1) % 4;
                    break;
                case 2:
                    while (arr[1][idx] != 0) {
                        if (idx >= N / 2) {
                            arr[0][idx] = number--;
                            RUN = false;
                            break;
                        }
                        idx++;
                    }
                    if (!RUN)
                        break;
                    arr[1][idx] = number--;
                    flag = (flag + 1) % 4;
                    break;
                case 3:
                    while (arr[1][N - idx - 1] != 0 && N - idx - 1 >= N / 2) {
                        idx++;
                    }
                    arr[1][N - idx - 1] = number--;
                    flag = (flag + 1) % 4;
                    break;
            }
        }

        for (int i = 1; i >= 0; i--) {
            for (int j = N - 2; j >= 1; j--) {
                arr[i][j] = number--;
            }
        }

        for (int i = 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                total -= arr[i][j];
                corruption += total * t++;
            }
        }

        res.append(corruption).append('\n');
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                res.append(arr[i][j]).append(' ');
            }
            res.append('\n');
        }

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
