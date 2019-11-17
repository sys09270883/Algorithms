/*
https://www.acmicpc.net/problem/1929
[����]
M�̻� N������ �Ҽ��� ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �ڿ��� M�� N�� �� ĭ�� ���̿� �ΰ� �־�����. (1 �� M �� N �� 1,000,000)

[���]
�� �ٿ� �ϳ���, �����ϴ� ������� �Ҽ��� ����Ѵ�.

[Ǯ��]
�����佺 �׳׽� ���� ��������..

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
