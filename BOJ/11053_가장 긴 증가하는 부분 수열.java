import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int[] arr, dp;

	public static void main(String... args) throws IOException {
		int N = io.nextInt(), res = 1;
		arr = new int[N + 1];   dp = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = io.nextInt();
		}

		dp[1] = arr[1];
		int idx = 1;
		for (int i = 2; i < N + 1; i++) {
			if (dp[idx] < arr[i])
				dp[++idx] = arr[i];
			else {
				int j = lowerBound(idx, arr[i]);
				dp[j] = arr[i];
			}
			res = Math.max(res, idx);
		}

		io.write(res);
	}

	private static int lowerBound(int end, int n) {
		int start = 0, ret = -1;
		while (start <= end) {
			int m = (start + end) / 2;
			if (dp[m] >= n) {
				end = m - 1;
				ret = m;
			}
			else
				start = m + 1;
		}
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