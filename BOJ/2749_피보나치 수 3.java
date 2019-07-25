/*
https://www.acmicpc.net/problem/2749
[문제]
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 n번째 피보나치 수를 1,000,000으로 나눈 나머지를 출력한다.

[풀이]
피보나치의 행렬식 {{F(n+1), F(n)}, {F(n}, F(n-1)} = {{1, 1}, {1, 0}}^n을 이용한다.
행렬의 제곱은 분할정복으로 한다.

+ 참고 : https://www.acmicpc.net/blog/view/28
+ 피사노 주기에 대해서 알아보자.
+ 큰 수를 다룰 때에는 오버플로우를 유의하자.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long N = Long.parseLong(br.readLine());
		long[][] fibo = {{1, 1}, {1, 0}};
		fibo = matrixPow(fibo, N);
		
		bw.write(Long.toString(fibo[0][1]));
		bw.close();
		br.close();	
	}

	private static long[][] matrixMul(long[][] a, long[][] b){
		long c[][] = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
				c[i][j] %= 1000000;
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
