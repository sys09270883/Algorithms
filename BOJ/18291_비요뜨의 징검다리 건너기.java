import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MOD = (int)1e9 + 7;

    public static void main(String... args) throws IOException {
    	int T = io.nextInt();
    	StringBuilder res = new StringBuilder();
    	while (T-- > 0) {
    		int N = io.nextInt();
    		res.append(N == 1 ? 1 : pow(2, N - 2)).append('\n');
    	}
    	io.write(res);
    }
    
    private static long pow(int a, int b) {
    	if (b == 0)
    		return 1;
    	long ret = pow(a, b / 2);
    	ret *= ret;
    	ret %= MOD;
    	if ((b & 1) == 1) {
    		ret *= a;
    		ret %= MOD;
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