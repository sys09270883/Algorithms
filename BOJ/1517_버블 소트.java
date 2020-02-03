import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static long res;
    static int[] arr, sorted;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        arr = new int[N]; sorted = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = io.nextInt();
        }

        mergeSort(0, N - 1);

        io.write(res);
    }

    private static void merge(int s, int m, int e) {
        int i = s, j = m + 1, k = s;

        while (i <= m && j <= e) {
            if (arr[i] <= arr[j])
                sorted[k++] = arr[i++];
            else {
                sorted[k++] = arr[j++];
                res += m - i + 1;
            }
        }

        while (i <= m) {
            sorted[k++] = arr[i++];
        }

        while (j <= e) {
            sorted[k++] = arr[j++];
        }

        while (s <= e) {
            arr[s] = sorted[s++];
        }
    }

    private static void mergeSort(int s, int e) {
        if (s < e) {
            int m = (s + e) >>> 1;
            mergeSort(s, m);
            mergeSort(m + 1, e);
            merge(s, m, e);
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