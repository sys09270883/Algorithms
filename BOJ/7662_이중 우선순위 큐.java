import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int T = io.nextInt(), Q, k;
        char c;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        StringBuilder res = new StringBuilder();
        while (T-- > 0) {
            tm.clear();
            Q = io.nextInt();
            for (int i = 0; i < Q; i++) {
                c = io.next().charAt(0);
                k = io.nextInt();
                if (c == 'I')
                    tm.put(k, tm.containsKey(k) ? tm.get(k) + 1 : 1);
                else {
                    if (tm.isEmpty())
                        continue;
                    int key = k == -1 ? tm.firstKey() : tm.lastKey();
                    int val = tm.get(key) - 1;
                    if (val == 0)
                        tm.remove(key);
                    else
                        tm.put(key, val);
                }
            }
            if (tm.isEmpty())
                res.append("EMPTY\n");
            else
                res.append(tm.lastKey()).append(' ').append(tm.firstKey()).append('\n');
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