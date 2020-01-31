import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static Point[] points;
    static Point first;

    public static void main(String... args) throws IOException {
        N = io.nextInt();
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(io.nextLong(), io.nextLong());
        }

        first = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            if (points[i].y < first.y) {
                first.x = points[i].x;
                first.y = points[i].y;
            }
            else if (points[i].y == first.y) {
                if (points[i].x < first.x) {
                    first.x = points[i].x;
                    first.y = points[i].y;
                }
            }
        }

        Arrays.sort(points);

        Stack<Point> stack = new Stack<>();
        stack.add(first);
        for (int i = 1; i < N; i++) {
            while (stack.size() > 1 && CCW(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points[i]) <= 0) {
                stack.pop();
            }
            stack.add(points[i]);
        }

        io.write(stack.size());
    }

    private static long CCW(Point p1, Point p2, Point p3) {
        long ret = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
        ret -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
        return ret > 0 ? 1 : ret == 0 ? 0 : -1;
    }

    private static long dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point point) {
            long r = CCW(first, this, point);
            return CCW(first, this, point) == 0 ? (dist(first, this) > dist(first, point) ? 1 : -1) : r < 0 ? 1 : -1;
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