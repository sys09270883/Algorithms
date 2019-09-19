/*
https://www.acmicpc.net/problem/10826
[����]
�Ǻ���ġ ���� 0�� 1�� �����Ѵ�. 0��° �Ǻ���ġ ���� 0�̰�, 1��° �Ǻ���ġ ���� 1�̴�. �� ���� 2��° ���ʹ� �ٷ� �� �� �Ǻ���ġ ���� ���� �ȴ�.

�̸� ������ �Ẹ�� Fn = Fn-1 + Fn-2 (n>=2)�� �ȴ�.

n=17�϶� ���� �Ǻ���ġ ���� �Ẹ�� ������ ����.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n�� �־����� ��, n��° �Ǻ���ġ ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� n�� �־�����. n�� 10,000���� �۰ų� ���� �ڿ��� �Ǵ� 0�̴�.

[���]
ù° �ٿ� n��° �Ǻ���ġ ���� ����Ѵ�.

[Ǯ��]
�Ǻ���ġ ��Ľ� {{1, 1}, {1, 0}}�� n ������ (0, 1)���� n ��° �Ǻ���ġ ���� ���� Ȱ���Ѵ�.
���� �������� ����� ������ ���Ѵ�.

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
