import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int N, M;
    static int[] m, c;
    static int[][] dp;
    
    public static void main(String... args) throws IOException {
    	N = io.nextInt();	M = io.nextInt();
    	m = new int[N + 1];	c = new int[N + 1];
    	dp = new int[N + 1][100 * N + 1];
    	for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
    	int low = 0, high = 0, res = Integer.MAX_VALUE;
    	for (int i = 1; i < N + 1; i++) {
    		m[i] = io.nextInt();
		}
    	for (int i = 1; i < N + 1; i++) {
			c[i] = io.nextInt();
			high += c[i];
		}
    	
    	while (low <= high) {
    		int m = (low + high) / 2;
    		if (func(N, m) >= M) {
    			res = Math.min(res, m);
    			high = m - 1;
    		}
    		else
    			low = m + 1;
    	}
    	
    	io.write(res);
    }
    
    private static int func(int idx, int cost) {
    	if (idx < 1)
    		return 0;
    	if (dp[idx][cost] != -1)
    		return dp[idx][cost];
    	dp[idx][cost] = func(idx - 1, cost);
    	if (cost - c[idx] >= 0)
    		dp[idx][cost] = Math.max(dp[idx][cost], func(idx - 1, cost - c[idx]) + m[idx]);
    	return dp[idx][cost];
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