import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    
    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int[][] arr = new int[N + 1][2];
        int[] dp = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
        	arr[i][0] = io.nextInt();
        	arr[i][1] = io.nextInt();
        }
        
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        
        dp[1] = 1;
        for (int i = 2; i < N + 1; i++) {
        	dp[i] = 1;
        	for (int j = 1; j < i; j++) {
				if (arr[i][1] > arr[j][1])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
			if (dp[i] > max)
				max = dp[i];
		}

        io.write(N - max);
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