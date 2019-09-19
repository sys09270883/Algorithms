/*
https://www.acmicpc.net/problem/10826
[문제]
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 n이 주어진다. n은 10,000보다 작거나 같은 자연수 또는 0이다.

[출력]
첫째 줄에 n번째 피보나치 수를 출력한다.

[풀이]
피보나치 행렬식 {{1, 1}, {1, 0}}의 n 제곱의 (0, 1)항이 n 번째 피보나치 수인 것을 활용한다.
분할 정복으로 행렬의 제곱을 구한다.

*/
import java.io.*;
import java.math.BigInteger;

public class Main {
	
	static BigInteger ZERO = BigInteger.ZERO;
	static BigInteger ONE = BigInteger.ONE;
	static BigInteger[][] FIBO = {{ONE, ONE}, {ONE, ZERO}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		bw.write(n != 0 ? matrixPow(FIBO, n)[0][1].toString() : "0");
		bw.close();
		br.close();
	}
	
	private static BigInteger[][] matrixMul(BigInteger[][] A, BigInteger[][] B) {
		BigInteger[][] C = {{ZERO, ZERO}, {ZERO, ZERO}};
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					C[i][j] = C[i][j].add(A[i][k].multiply((B[k][j])));
				}
			}
		}
		
		return C;
	}
	
	private static BigInteger[][] matrixPow(BigInteger[][] A, int n) {
		if (n == 1)
			return A;
		
		A = matrixPow(A, n / 2);
		A = matrixMul(A, A);
		
		if (n % 2 == 1)
			A = matrixMul(A, FIBO);
		
		return A;
	}
	
}
