import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        long N = io.nextLong(), K = io.nextLong(), size = N, idx = N - 1;
        List<Integer> list = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        if ((N - 1) * N / 2 < K) {
            io.write(-1);
            return;
        }

        else if ((N - 1) * N / 2 == K) {
            for (int i = (int)N; i >= 1; i--) {
                res.append(i).append(' ');
            }
        }

        else if (K == 0) {
            for (int i = 1; i <= N; i++) {
                res.append(i).append(' ');
            }
        }

        else {
            while (true) {
                if (size <= K) {
                    res.append(list.get((int)(idx--))).append(' ');
                    K = K - (--size);
                }

                else {
                    list.add((int)(size - K - 1), list.get((int)idx));
                    for (int i = 0; i <= idx; i++) {
                        res.append(list.get(i)).append(' ');
                    }
                    break;
                }
            }
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