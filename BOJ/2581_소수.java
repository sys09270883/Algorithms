import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int M = io.nextInt(), N = io.nextInt(), sum = 0;
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        List<Integer> primeList = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (int i = 2; i * i < N + 1; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i + i; j < N + 1; j += i) {
                isPrime[j] = false;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if (isPrime[i])
                primeList.add(i);
        }

        int idx = Collections.binarySearch(primeList, M);
        idx = idx < 0 ? -(idx + 1) : idx;
        int size = primeList.size();
        for (int i = idx; i < size; i++) {
            sum += primeList.get(i);
        }

        if (sum == 0)
            io.write(-1);
        else {
            res.append(sum).append('\n').append(primeList.get(idx));
            io.write(res);
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