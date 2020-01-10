import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        long n = io.nextLong();
        long min = 0, max = 0, cnt = count(n);
        StringBuilder res = new StringBuilder();

        for (long i = 1; i <= n; i <<= 1) {
            if (count(n - i ) == cnt) {
                min = func1(n - i, i);
                break;
            }
        }

        long i = 1;
        while (true) {
            if (count(n + i) == cnt) {
                max = func2(n + i, i << 1);
                break;
            }
            i <<= 1;
        }
        res.append(min).append(' ').append(max);
        io.write(res);
    }

    private static long count(long n) {
        long cnt = 0;
        while (n > 0) {
            if (n % 2 == 1)
                cnt++;
            n >>= 1;
        }
        return cnt;
    }

    private static long func2(long a, long b) {
        long s = 0, c;
        while (b <= a) {
            if ((a & b) != 0) s += b;
            b <<= 1;
        }
        long x = count(a);
        long y = count(s);
        c = 1;
        while (x - y != 0) {
            s += c;
            y++;
            c <<= 1;
        }
        return s;
    }

    private static long func1(long a, long b) {
        long s = 0, c = b;
        while (b <= a) {
            if ((a & b) != 0) s += b;
            b <<= 1;
        }
        long x = count(a);
        long y = count(s);
        while (x - y != 0) {
            c >>= 1;
            s += c;
            y++;
        }
        return s;
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
