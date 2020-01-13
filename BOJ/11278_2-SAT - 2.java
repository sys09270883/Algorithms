import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] arr;
    static int[][] c;
    static boolean flag;
    static StringBuilder res;

    public static void main(String... args) throws IOException {
        N = io.nextInt();   M = io.nextInt();
        arr = new int[N + 1];
        c = new int[M][2];
        res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            c[i][0] = io.nextInt();
            c[i][1] = io.nextInt();
        }

        func(1);
        io.write(flag ? res.toString() : "0");
    }

    private static void func(int depth) {
        if (flag)
            return;
        if (depth > N) {
            int sleft = c[0][0] < 0 ? -arr[-c[0][0]] + 1: arr[c[0][0]];
            int sright = c[0][1] < 0 ? -arr[-c[0][1]] + 1: arr[c[0][1]];
            int f = sleft | sright;

            for (int i = 1; i < M; i++) {
                int left = c[i][0] < 0 ? -arr[-c[i][0]] + 1: arr[c[i][0]];
                int right = c[i][1] < 0 ? -arr[-c[i][1]] + 1: arr[c[i][1]];
                f &= (left | right);
            }
            if (f == 1) {
                flag = true;
                res.append("1\n");
                for (int j = 1; j < N + 1; j++) {
                    res.append(arr[j]).append(' ');
                }
                return;
            }

            return;
        }

        func(depth + 1);
        arr[depth] = 1;
        func(depth + 1);
        arr[depth] = 0;
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