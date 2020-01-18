import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[] num;
    static int ans, strLen;

    public static void main(String... args) throws IOException {
        String str = io.next();
        strLen = str.length();
        num = new int[strLen];
        for (int i = 0; i < strLen; i++) {
            num[i] = str.charAt(i) - '0';
        }
        func(0);
        io.write(ans);
    }

    private static void func(int len) {
        if (len > strLen)
            return;
        else if (len == strLen) {
            ans++;
            return;
        }

        if (num[len] != 0)
            func(len + 1);
        if (len + 2 <= strLen) {
            if (num[len] != 0 && num[len] * 10 + num[len + 1] <= 34)
                func(len + 2);
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