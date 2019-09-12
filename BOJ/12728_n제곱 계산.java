/*
https://www.acmicpc.net/problem/12728
[����]
�� �������� ���� (3 + ��5)n �� ���� �Ҽ��� �տ� ������ �� �ڸ��� ã�ƾ��մϴ�. ���� ���, n = 5 �� �� (3 + ��5)5  = 3935.73982 ... �̹Ƿ� ���� 935�Դϴ�. 
n = 2 �� ��� (3 + ��5)2 = 27.4164079 �� �̹Ƿ�, ���� 027�Դϴ�.

[�Է�]
ù ��° �Է� ���� �׽�Ʈ ���̽��� �� T�� �˷��ݴϴ�. ������ T �׽�Ʈ ���̽��� ��(����)�� ���ҵǾ� �־�����, �� �׽�Ʈ ���̽��� �ϳ��� ���� ���� n�� �˷��ݴϴ�.

����

1 <= T <= 100
2 <= n <= 2000000000
[���]
�� �Է� �׽�Ʈ ���̽��� ���� ������ ���� ����� �ʿ��մϴ�.

Case #X: Y
���⼭ X�� �׽�Ʈ ���̽� ��ȣ�̰�, Y�� ���� (3 + (5)^(0.5))^n �� ������ �� �����Դϴ�. 
���� �Ҽ��� ���� ����(����)�� �� �ڸ����� ���� ��� ��¿� ��Ȯ�� �� �ڸ��� ���Եǵ��� �տ� 0�� �߰��Ͻʽÿ�.

[Ǯ��]
A(n) = pow(3 + sqrt(5), n), B(n) = pow(3 - sqrt(5), n)�̶� ����.
X(n) = A(n) + B(n)�� ��������. (X(n)�� ���������� ���� �׻� �����̴�.)

A(n) = a(n) + b(n) * sqrt(5)�� ǥ���� �� �ִ�. (a(n), b(n) ���� ���������� ���� �����̴�.)
A(n + 1) = A(n) * A(1) = (a(n) + b(n) * sqrt(5)) * (3 + sqrt(5))
         = (3 * a(n) + 5 * b(n)) + (a(n) + 3 * b(n)) * sqrt(5)
       
���� a(n + 1) = 3 * a(n) + 5 * b(n), b(n + 1) = a(n) + 3 * b(n)�̴�.
�̴� ��Ľ� A = {{3, 5}, {1, 3}}���� ǥ���� �� ������, a(0) = 1, b(0) = 0�̴�.
{a(n + 1), b(n + 1)} = matrixPow(A, n) * {1, 0}�̴�.

����, B(n) = pow(3 - sqrt(5), n)�� 0�� �Ѿ��� ����� ���� ���̹Ƿ� X(n) = 2 * a(n)�� �����ϴ�.
X(n)�� ���� ��, ���� ���� ������ ������ ����Ƿ� ���ϴ� ���� 1�� ���־�� �Ѵ�. (������ ���� �� �����Ƿ� 999�� ���ϰ� 1000�� �������� ���Ѵ�).

 + ���� : https://code.google.com/codejam/contest/dashboard?c=32016#s=a&a=2
 
*/
import java.io.*;

public class Main {

	static int[][] DEFAULT_MATRIX = {{3, 5}, {1, 3}};
	final static int MOD = 1000; 
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine()), n;
		int[][] ans;
		StringBuilder res = new StringBuilder();
		
		for (int i = 1; i <= T; i++) {
			n = Integer.parseInt(br.readLine());
			ans = matrixPow(DEFAULT_MATRIX, n);
			assemble(res, i, ans);
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static int[][] matrixMul(int[][] A, int[][] B) {
		int[][] C = new int[2][2];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					C[i][j] += (A[i][k] * B[k][j]) % MOD;
				}
			}
		}
		
		return C;
	}
	
	private static int[][] matrixPow(int[][] A, int n) {
		if (n == 1)
			return A;
		
		A = matrixPow(A, n / 2);
		A = matrixMul(A, A);
		
		if (n % 2 == 1)
			A = matrixMul(A, DEFAULT_MATRIX);
		
		return A;
	}
	
	private static void assemble(StringBuilder res, int i, int[][] ans) {
		res.append("Case #" + i + ": " + String.format("%3s", 
				String.valueOf((ans[0][0] * 2 + 999) % MOD)).replace(' ', '0')).append('\n');
	}
}