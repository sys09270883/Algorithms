import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, tot;
    static int[] arr;
    static Set<Integer> hs;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N];
        hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = io.nextInt();
            hs.add(arr[i]);
            tot += arr[i];
        }
        func(0, arr[0]);
        func(0, 0);
        io.write(tot - hs.size() + 1);
    }

    private static void func(int cur, int val) {
        hs.add(Math.abs(val));
        int next = cur + 1;
        if (next >= N)
            return;
        func(cur + 1, val + arr[next]);
        func(cur + 1, val);
        func(cur + 1, val - arr[next]);
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