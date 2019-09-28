/*
https://www.acmicpc.net/problem/2688
[����]
� ���ڰ� �پ���� �ʴ´ٴ� ���� �� ������ �� �ڸ� ������ �� ���� �ڸ� ���� �۰ų� ���� �� �̴�.

���� ���, 1234�� �پ���� �ʴ´�. 

�پ���� �ʴ� 4�ڸ� ���� ���� ��� ���� 0011, 1111, 1112, 1122, 2223�� �ִ�. �پ���� �ʴ� 4�ڸ����� �� 715���� �ִ�.

�� ���������� ������ �տ� 0(leading zero)�� �־ �ȴ�. 0000, 0001, 0002�� �ùٸ� �پ���� �ʴ� 4�ڸ����̴�.

n�� �־����� ��, �پ���� �ʴ� n�ڸ� ���� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� T(1 <= T <= 1,000)�� �־�����. �� �׽�Ʈ ���̽��� ���� �ϳ� n���� �̷���� �ִ�. (1 <= n <= 64)

[���]
�� �׽�Ʈ ���̽��� ���� �� �ٿ� �ϳ��� �پ���� �ʴ� n�ڸ� ���� ������ ����Ѵ�.

[Ǯ��]
ó������ ť�� ���� ���ڵ��� �����鼭 �ش� 64�ڸ������� ���� �� ���س������� �ߴµ� �������� ť�� �� ���� �ʹ� ���Ҵ�.
������ ���� �����ϰ� ���� ���� ���̴� ������ �����ϸ� ���װ���� ���ϴ� �İ� ����.
 + ������ ��� ��ü�� ���װ���� ������ ������ ���װ�� ��ȭ������ �����ߴ�.

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
