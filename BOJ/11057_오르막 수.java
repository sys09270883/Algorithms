/*
https://www.acmicpc.net/problem/11057
[문제]
오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.

예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.

수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.

[입력]
첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.

[출력]
첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.

[풀이]
끝 자리수가 9라면 그 앞자리수는 0 ~ 8이여야 한다. 
이러한 특성을 dp로 표현하면
dp[자릿수][끝자리의숫자]로 표현할 수 있다. (1 <= 자리수 <= N, 0 <= 끝자리숫자 <= 0)
dp[1][0 ~ 9]이며 이 값은 한자리 숫자를 의미하므로 각각 1로 초기화 해준다.
bottom-up으로 dp[N]을 채워 넣고, dp[N][0 ~ 9]의 값들을 더하고 10007로 나누어 준다.

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
