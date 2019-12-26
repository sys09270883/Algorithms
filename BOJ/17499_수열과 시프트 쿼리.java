import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int Q = io.nextInt();
        int start = 0, query;
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < N; i++) {
            list.add(io.nextInt());
        }

        for (int i = 0; i < Q; i++) {
            query = io.nextInt();
            switch (query) {
                case 1:
                    int idx = (start + N + io.nextInt() - 1) % N, x = io.nextInt();
                    list.set(idx, list.get(idx) + x);
                    break;

                case 2:
                    int s = io.nextInt();
                    start = (start + N - s) % N;
                    break;

                case 3:
                    s = io.nextInt();
                    start = (start + s) % N;
                    break;
            }
        }

        for (int i = 0; i < N; i++) {
            res.append(list.get((i + start) % N)).append(' ');
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

    void close() throws IOException {
        bw.close();
        br.close();
    }
}