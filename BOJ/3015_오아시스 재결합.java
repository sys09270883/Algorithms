import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        long res = 0;
        Stack<Person> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int h = io.nextInt();

            while (!stack.isEmpty() && stack.peek().h < h) {
                res += stack.pop().num;
            }

            if (stack.isEmpty()) {
                stack.add(new Person(h, 1));
                continue;
            }

            if (stack.peek().h == h) {
                Person tmp = stack.pop();
                res += stack.isEmpty() ? tmp.num++ : tmp.num++ + 1;
                stack.add(tmp);
            }
            else {
                stack.add(new Person(h, 1));
                res++;
            }
        }

        io.write(res);
    }

}

class Person {
    int h, num;

    public Person(int h, int num) {
        this.h = h;
        this.num = num;
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