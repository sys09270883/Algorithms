import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static final String pattern = "Kingdom of ";
    static String[] arr;
    static Map<String, Integer> hm;
    static Set<String> ts;
    static int[] parent;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        arr = new String[N + 1];
        parent = new int[N + 1];
        Arrays.setAll(parent, i -> i);
        hm = new HashMap<>();
        ts = new TreeSet<>();
        int A, B, C;
        StringTokenizer st1, st2;
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            io.next();  io.next();  arr[i] = io.next();
            hm.put(arr[i], i);
        }

        for (int i = 0; i < M; i++) {
            st1 = new StringTokenizer(io.nextLine(), ",");
            st2 = new StringTokenizer(st1.nextToken());
            st2.nextToken();    st2.nextToken();
            A = hm.get(st2.nextToken());
            st2 = new StringTokenizer(st1.nextToken());
            st2.nextToken();    st2.nextToken();
            B = hm.get(st2.nextToken());
            C = Integer.parseInt(st1.nextToken());

            if (find(A) != find(B)) {
                if (C == 1)
                    union(find(A), find(B));
                else
                    union(find(B), find(A));
            }
            else {
                if (C == 1) {
                    union(A, B);
                    parent[A] = A;
                }
                else {
                    union(B, A);
                    parent[B] = B;
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (i == parent[i])
                ts.add(arr[i]);
        }

        res.append(ts.size()).append('\n');
        for (String s : ts) {
            res.append(pattern).append(s).append('\n');
        }

        io.write(res);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        if (x != y)
            parent[y] = x;
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