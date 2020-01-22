import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N;
    static int[] arr, tree;

    public static void main(String... args) throws IOException {
    	StringBuilder res = new StringBuilder();
    	while ((N = io.nextInt()) != 0) {
    		arr = new int[N + 1];
    		tree = new int[1 << (int)Math.ceil(Math.log(N) / Math.log(2)) + 1];
    		for (int i = 1; i < N + 1; i++) {
				arr[i] = io.nextInt();
			}
    		
    		init(1, 1, N);
			res.append(maxArea(1, N)).append('\n');
		}
    	
    	io.write(res);
    }
    
    private static int minIdx(int a, int b) {
    	if (a == -1)
    		return b;
    	if (b == -1)
    		return a;
    	return arr[a] <= arr[b] ? a : b;
    }
    
    private static int init(int node, int start, int end) {
		if (start == end)
			return tree[node] = start;
		int m = (start + end) / 2;
		return tree[node] = minIdx(init(node * 2, start, m), init(node * 2 + 1, m + 1, end));
	}
    
    private static int query(int left, int right, int node, int start, int end) {
    	if (left > end || right < start)
    		return -1;
    	if (left <= start && end <= right)
    		return tree[node];
    	int m = (start + end) / 2;
    	return minIdx(query(left, right, node * 2, start, m), query(left, right, node * 2 + 1, m + 1, end));
    }

    private static long maxArea(int left, int right) {
    	int idx = query(left, right, 1, 1, N);
    	long ret = (long)(right - left + 1) * arr[idx];
    	if (left < idx)
    		ret = Math.max(ret, maxArea(left, idx - 1));
    	if (idx < right)
    		ret = Math.max(ret, maxArea(idx + 1, right));
    	return ret;
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