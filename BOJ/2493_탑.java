import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        Stack<Node> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int t = io.nextInt();
            while (!stack.isEmpty()) {
                if (stack.peek().val > t) {
                    res.append(stack.peek().idx).append(' ');
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) {
                res.append(0).append(' ');
            }
            stack.add(new Node(i + 1, t));
        }

        io.write(res);
    }

}

class Node {
    int idx, val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
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
