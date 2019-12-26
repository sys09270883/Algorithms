import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static long INF = 1l << 31;

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int M = io.nextInt();
        int K = io.nextInt();
        long low = -1, high = INF, ans = INF;
        List<Beer> beerList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            beerList.add(new Beer(io.nextInt(), io.nextInt()));
        }
        Collections.sort(beerList);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        while (low <= high) {
            pq.clear();
            long m = (low + high) / 2;
            long sum = 0;

            for (Beer beer : beerList) {
                if (m >= beer.c)
                    pq.add(beer.v);

                else
                    break;
            }

            if (pq.size() < N) {
                low = m + 1;
                continue;
            }

            for (int i = 0; i < N; i++) {
                sum += pq.poll();
            }

            if (sum >= M) {
                ans = Math.min(ans, m);
                high = m - 1;
            }

            else
                low = m + 1;
        }

        io.write(ans == INF ? -1 : ans);
    }

}

class Beer implements Comparable<Beer> {
    int v, c;

    public Beer(int v, int c) {
        this.v = v;
        this.c = c;
    }

    @Override
    public int compareTo(Beer o) {
        if (this.c == o.c)
            return o.v - this.v;
        return this.c - o.c;
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