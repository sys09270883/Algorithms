import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, cnt;
    static int[] arr;
    static boolean[] visited, finished;


    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            N = io.nextInt();
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            for (int i = 1; i < N + 1; i++) {
                arr[i] = io.nextInt();
            }

            cnt = 0;
            for (int i = 1; i < N + 1; i++) {
                if (!visited[i])
                    DFS(i);
            }

            res.append(N - cnt).append('\n');
        }

        io.write(res);
    }

    private static void DFS(int cur) {
        visited[cur] = true;
        int next = arr[cur];
        if (visited[next]) {
            if (!finished[next]) {
                for (int i = next; i != cur; i = arr[i]) {
                    cnt++;
                }
                cnt++;
            }
        }
        else
            DFS(next);
        finished[cur] = true;
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