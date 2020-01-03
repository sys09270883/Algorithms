import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MOD = 123456789, MAX = 40001;
    static List<Integer> primeList;

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        primeList = new ArrayList<>();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 2; i < MAX; i++) {
            if (!isPrime[i])
                continue;

            for (int j = i << 1; j < MAX; j += i) {
                isPrime[j] = false;
            }
        }
        for (int i = 2; i < MAX; i++) {
            if (isPrime[i])
                primeList.add(i);
        }

        for (Integer i : primeList) {
            for (int j = i; j <= N; j++) {
                dp[j] = (dp[j] + dp[j - i]) % MOD;
            }
        }

        io.write(dp[N]);
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