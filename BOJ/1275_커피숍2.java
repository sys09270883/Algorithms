import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, Q, x, y, a, b;
    static int[] arr;
    static long[] tree;

    public static void main(String... args) throws IOException {
    	N = io.nextInt();	Q = io.nextInt();
    	arr = new int[N + 1];
    	tree = new long[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
    	StringBuilder res = new StringBuilder();
    	for (int i = 1; i < N + 1; i++) {
			arr[i] = io.nextInt();
		}
    	
    	init(1, 1, N);
    	
    	for (int i = 0; i < Q; i++) {
			x = io.nextInt();	y = io.nextInt();	a = io.nextInt();	b = io.nextInt();
			if (x > y) {
				x ^= y;
				y ^= x;
				x ^= y;
			}
			res.append(query(x, y, 1, 1, N)).append('\n');
			
			long diff = (long)b - arr[a];
			arr[a] = b;
			update(a, diff, 1, 1, N);
		}
    	
    	io.write(res);
    }
    
    private static long init(int node, int start, int end) {
    	if (start == end)
    		return tree[node] = arr[start];
    	int m = (start + end) / 2;
    	return tree[node] = init(node * 2, start, m) + init(node * 2 + 1, m + 1, end);
    }
    
    private static long query(int left, int right, int node, int start, int end) {
    	if (left > end || right < start)
    		return 0;
    	if (left <= start && end <= right)
    		return tree[node];
    	int m = (start + end) / 2;
    	return query(left, right, node * 2, start, m) + query(left, right, node * 2 + 1, m + 1, end);
    }
    
    private static void update(int idx, long diff, int node, int start, int end) {
    	if (idx > end || idx < start)
    		return;
    	tree[node] += diff;
    	if (start == end)
    		return;
    	int m = (start + end) / 2;
    	update(idx, diff, node * 2, start, m);
    	update(idx, diff, node * 2 + 1, m + 1, end);
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