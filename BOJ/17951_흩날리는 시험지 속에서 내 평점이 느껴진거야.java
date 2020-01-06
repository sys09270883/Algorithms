import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), K = io.nextInt(), low = 0, high = 0, ans = -1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = io.nextInt();
            high += arr[i];
        }

        while (low <= high) {
            int m = (low + high) / 2;
            int s = 0, diff = Integer.MAX_VALUE, cnt = 1;
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                s += arr[i];
                if (s >= m) {
                    diff = Math.min(diff, s);
                    s = 0;
                    cnt++;
                    if (cnt > K) {
                        ans = Math.max(ans, diff);
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                low = m + 1;
                ans = Math.max(ans, diff);
            }
            else
                high = m - 1;
        }

        io.write(ans);
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
