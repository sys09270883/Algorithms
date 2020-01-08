import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
    	int N = io.nextInt(), P = io.nextInt(), T = io.nextInt(), t = 1;
    	int a = 0, b = 0, cnt = 0;
    	for (int i = 1; i < T + 1; i++) {
			cnt += t;
			a = b;
			b += cnt;
			if (cnt == 2 * N || (cnt == 1 && i != 1))
				t = -t;
			if (b > 2 * N)
				b -= 2 * N;
		}
    	
    	a = (a + 1) / 2;
    	b = (b + 1) / 2;
    	
    	if (a > b)
    		io.write(P > a || P <= b ? "Dehet YeonJwaJe ^~^" : "Hing...NoJam");
    	else
    		io.write(a < P && P <= b ? "Dehet YeonJwaJe ^~^" : "Hing...NoJam");
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
