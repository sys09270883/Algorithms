import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, P, E;
    static int[][] arr;
    static LinkedList<Integer> idxList;
    static boolean flag = false;
    static StringBuilder res = new StringBuilder();

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        P = io.nextInt();
        E = io.nextInt();
        arr = new int[N + 1][2];
        idxList = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            arr[i][0] = io.nextInt();
            arr[i][1] = io.nextInt();
        }

        func(0, 0, 0);
        io.write(res.append(flag ? "" : -1));
    }

    private static void func(int min, int max, int idx) {
        if (flag)
            return;
        else if (idxList.size() == P) {
            int[] ans = new int[N + 1];

            if (min <= E && E <= max) {
                for (Integer i : idxList) {
                    ans[i] = arr[i][0];
                    E -= ans[i];
                }

                for (int i = 0; i < N + 1 && E > 0; i++) {
                    if (ans[i] > 0) {
                        int diff = arr[i][1] - arr[i][0];

                        if (diff >= E) {
                            ans[i] += E;
                            E = 0;
                        }
                        else {
                            ans[i] += diff;
                            E -= diff;
                        }
                    }
                }

                for (int i = 1; i < N + 1; i++) {
                    res.append(ans[i]).append(' ');
                }
                flag = true;
            }
            return;
        }
        else if (idx + 1 > N)
            return;

        // 현재 미포함
        func(min, max, idx + 1);
        // 현재 포함
        idxList.add(idx + 1);
        func(min + arr[idx + 1][0], max + arr[idx + 1][1], idx + 1);
        idxList.pollLast();
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