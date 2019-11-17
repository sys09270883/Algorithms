/*
https://www.acmicpc.net/problem/1929
[문제]
M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000)

[출력]
한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.

[풀이]
에라토스 테네스 구현 연습문제..

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	final static int MAX = 1000001; 
	
	public static void main(String... args) throws IOException {
		int M = io.nextInt();
		int N = io.nextInt();
		boolean[] isPrime = new boolean[MAX];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		StringBuilder res = new StringBuilder();
		
		for (int i = 2; i < Math.sqrt(MAX); i++) {
			for (int j = 2; j < MAX; j++) {
				if (i * j >= MAX)
					break;
				
				isPrime[i * j] = false;
			}
		}
		
		for (int i = M; i <= N; i++) {
			if (isPrime[i])
				res.append(i).append('\n');
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
