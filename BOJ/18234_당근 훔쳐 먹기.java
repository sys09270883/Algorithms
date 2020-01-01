import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        long N = io.nextLong(), T = io.nextLong(), ans = 0l;
        PriorityQueue<Carrot> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new Carrot(io.nextLong(), io.nextLong()));
        }

        for (int i = 0; i < N; i++) {
            Carrot c = pq.poll();
            ans += (T - N + i) * c.p + c.w;
        }

        io.write(ans);
    }

}

class Carrot implements Comparable<Carrot> {
    long w, p;

    public Carrot(long w, long p) {
        this.w = w;
        this.p = p;
    }

    @Override
    public int compareTo(Carrot o) {
        if (this.p == o.p)
            return (int)(o.w - this.w);
        return (int)(this.p - o.p);
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