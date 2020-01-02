import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int[] table;
    static int N, cur = Integer.MAX_VALUE;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        int M = io.nextInt(), o;
        table = new int[N + 1];
        String input = null;
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < N; i++) {
            input = io.nextLine();
            int len = input.length();
            for (int j = 0; j < len; j++) {
                table[i] |= 1 << (input.charAt(j) - 'a');
            }
        }

        while (M-- > 0) {
            o = io.nextInt();
            char x = io.next().charAt(0);

            if (o == 1)
                cur &= ~(1 << (x - 'a'));
            else
                cur |= 1 << (x - 'a');

            res.append(count()).append('\n');
        }

        io.write(res);
    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((table[i] & cur) == table[i])
                cnt++;
        }
        return cnt;
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

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
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

    void write(char[] str) throws IOException {
        int len = str.length;
        for (int i = 0; i < len; i++) {
            bw.write(str[i]);
        }
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}