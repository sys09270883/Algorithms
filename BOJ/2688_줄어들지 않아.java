/*
https://www.acmicpc.net/problem/2688
[문제]
어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리 수가 작거나 같을 때 이다.

예를 들어, 1234는 줄어들지 않는다. 

줄어들지 않는 4자리 수를 예를 들어 보면 0011, 1111, 1112, 1122, 2223이 있다. 줄어들지 않는 4자리수는 총 715개가 있다.

이 문제에서는 숫자의 앞에 0(leading zero)이 있어도 된다. 0000, 0001, 0002는 올바른 줄어들지 않는 4자리수이다.

n이 주어졌을 때, 줄어들지 않는 n자리 수의 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 T(1 <= T <= 1,000)이 주어진다. 각 테스트 케이스는 숫자 하나 n으로 이루어져 있다. (1 <= n <= 64)

[출력]
각 테스트 케이스에 대해 한 줄에 하나씩 줄어들지 않는 n자리 수의 개수를 출력한다.

[풀이]
처음에는 큐에 직접 숫자들을 넣으면서 해당 64자리까지의 수를 다 구해놓으려고 했는데 생각보다 큐에 들어갈 수가 너무 많았다.
마지막 수를 고정하고 앞의 수를 붙이는 식으로 생각하면 이항계수를 구하는 식과 같다.
 + 예제의 출력 자체가 이항계수와 관련이 깊어보여서 이항계수 점화식으로 접근했다.

*/
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()), n;
		long ans;
		StringBuilder res = new StringBuilder();
		long[][] dp = new long[66][10];
		
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
			dp[2][i] = i + 1;
		}
		
		while (T-- > 0) {
			n = Integer.parseInt(br.readLine());
			ans = 0l;
			
			for (int i = 3; i <= n; i++) {
				dp[i][0] = 1;
				
				for (int j = 1; j < 10; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
			
			for (int i = 0; i < 10; i++) {
				ans += dp[n][i];
			}
			
			res.append(ans).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
