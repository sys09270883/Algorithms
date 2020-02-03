import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, mcnt, res;
    static int[] arr, left, right;
    static char[] op;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N + 1];
        left = new int[N];
        right = new int[N];
        op = new char[N];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = io.nextInt();
        }
        for (int i = 1; i < N; i++) {
            op[i] = io.next().charAt(0);
            left[i] = io.nextInt();
            right[i] = io.nextInt();
        }

        Arrays.sort(arr, 1, N + 1);
        DFS(2 * N - 1, true);
        for (int i = 1; i < N + 1; i++) {
            res += (i <= mcnt ? -1 : 1) * arr[i];
        }
        io.write(res);
    }

    private static void DFS(int idx, boolean flag) {
        if (idx <= N) {
            if (!flag)
                mcnt++;
            return;
        }
        DFS(left[idx - N], flag);
        DFS(right[idx - N], op[idx - N] == '+' ? flag : !flag);
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