import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static String input;

    public static void main(String... args) throws IOException {
        input = io.next();
        int N = io.nextInt(), pointer = 0, size = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String type = io.next();

            if (type.equals("char")) {
                size = 2;
                res.append(func(pointer, size)).append(' ');
                pointer += 2;
            }
            else if (type.equals("int")) {
                size = 8;
                res.append(func(pointer, size)).append(' ');
                pointer += 8;
            }
            else {
                size = 16;
                res.append(func(pointer, size)).append(' ');
                pointer += 16;
            }
        }

        io.write(res);
    }

    private static String func(int pointer, int size) {
        BigInteger num = new BigInteger(input.substring(pointer, pointer + size), 16);
        return num.toString();
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
