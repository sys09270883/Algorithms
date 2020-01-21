import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();
        List<Integer> fiboList = new ArrayList<>();
        int prev = 1, cur = 2, next = prev + cur, max = (int)1e9;
        fiboList.add(prev);
        fiboList.add(cur);
        while (next <= max) {
            fiboList.add(next);
            prev = cur;
            cur = next;
            next = prev + cur;
        }
        Stack<Integer> stack = new Stack<>();

        while (T-- > 0) {
            stack.clear();
            int target = io.nextInt();
            while (target > 0) {
                int idx = Collections.binarySearch(fiboList, target);
                idx = idx < 0 ? -(idx + 2) : idx;
                int val = fiboList.get(idx);
                stack.add(val);
                target -= val;
            }
            while (!stack.isEmpty()) {
                res.append(stack.pop()).append(' ');
            }
            res.append('\n');
        }

        io.write(res);
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