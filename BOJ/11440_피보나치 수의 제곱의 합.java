/*
https://www.acmicpc.net/problem/11440
[문제]
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, 0번째 피보나치 수의 제곱부터 n번째 피보나치 수의 제곱을 합한 값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 0번째 피보나치 수의 제곱부터 n번째 피보나치 수의 제곱의 합을 1,000,000,007으로 나눈 나머지를 출력한다.

[풀이]
F(n) = -F(n-1) + F(n)의 양 변에 F(n)을 곱하고 시그마 처리를 하면 n^2까지의 합은 F(n)*F(n+1)이다.
입력이 long 범위이므로 n번째 항과 n+1번째 항을 구하고 1000000007로 나누어 준다.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	final static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long n = Long.parseLong(br.readLine());
		
		long[][] fibo = {{1, 1}, {1, 0}};
		long[][] X = matrixPow(fibo, n);
		long[][] Y = matrixPow(fibo, n+1);
		
		bw.write(String.valueOf(X[0][1] * Y[0][1] % MOD));
		bw.close();
		br.close();	
	}
	
	private static long[][] matrixMul(long[][] a, long[][] b){
		long c[][] = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] += (a[i][k] * b[k][j]) % MOD;
				}
				c[i][j] %= MOD;
			}
		}

		return c;
	}

	private static long[][] matrixPow(long[][] a, long b){
		if(b <= 1)
			return a;
		
		a = matrixPow(a, b/2);
		a = matrixMul(a, a);

		if(b % 2 == 1){
			long[][] tmp = {{1, 1}, {1, 0}};
			a = matrixMul(a, tmp);
		}

		return a;
	}
}
