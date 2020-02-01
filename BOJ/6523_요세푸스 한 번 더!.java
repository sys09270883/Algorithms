import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static long N, a, b;

    public static void main(String... args) throws IOException {
        StringBuilder res = new StringBuilder();

        while ((N = io.nextInt()) != 0) {
            a = io.nextLong();  b = io.nextLong();
            HashMap<Integer, Integer> hm = new HashMap<>();
            int start = 0, cnt = 0;

            while (true) {
                Integer next = (int)calc(start);

                if (hm.get(next) == null)
                    hm.put(next, 1);
                else
                    hm.put(next, hm.get(next) + 1);

                if (hm.get(next) == 3)
                    break;

                start = next;
            }

            for (Integer i : hm.keySet()) {
                if (hm.get(i) == 1)
                    cnt++;
            }

            res.append(N - hm.size() + cnt).append('\n');
        }

        io.write(res);
    }

    private static long calc(long x) {
        return ((a * x % N) * (x % N) + Main.b) % N;
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