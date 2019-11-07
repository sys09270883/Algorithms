/*
https://www.acmicpc.net/problem/11057
[����]
������ ���� ���� �ڸ��� ���������� �̷�� ���� ���Ѵ�. �̶�, ������ ���� ���Ƶ� ������������ ģ��.

���� ���, 2234�� 3678, 11119�� ������ ��������, 2232, 3676, 91111�� ������ ���� �ƴϴ�.

���� ���� N�� �־����� ��, ������ ���� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�. ���� 0���� ������ �� �ִ�.

[�Է�]
ù° �ٿ� N (1 �� N �� 1,000)�� �־�����.

[���]
ù° �ٿ� ���̰� N�� ������ ���� ������ 10,007�� ���� �������� ����Ѵ�.

[Ǯ��]
�� �ڸ����� 9��� �� ���ڸ����� 0 ~ 8�̿��� �Ѵ�. 
�̷��� Ư���� dp�� ǥ���ϸ�
dp[�ڸ���][���ڸ��Ǽ���]�� ǥ���� �� �ִ�. (1 <= �ڸ��� <= N, 0 <= ���ڸ����� <= 0)
dp[1][0 ~ 9]�̸� �� ���� ���ڸ� ���ڸ� �ǹ��ϹǷ� ���� 1�� �ʱ�ȭ ���ش�.
bottom-up���� dp[N]�� ä�� �ְ�, dp[N][0 ~ 9]�� ������ ���ϰ� 10007�� ������ �ش�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	
	public static void main(String... args) throws IOException {
		int N = io.nextInt(), MOD = 10007, ans = 0;
		int[][] dp = new int[N + 1][10];
		Arrays.fill(dp[1], 1);
		
		for (int i = 2; i < N + 1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
					dp[i][j] %= MOD;
				}
			}
		}
		
		for (int i = 0; i < 10; i++) {
			ans += dp[N][i];
		}
		
		io.write(ans % MOD);
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
