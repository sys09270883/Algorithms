/*
https://www.acmicpc.net/problem/12728
[문제]
이 문제에서 숫자 (3 + √5)n 에 대한 소수점 앞에 마지막 세 자리를 찾아야합니다. 예를 들어, n = 5 일 때 (3 + √5)5  = 3935.73982 ... 이므로 답은 935입니다. 
n = 2 인 경우 (3 + √5)2 = 27.4164079 … 이므로, 답은 027입니다.

[입력]
첫 번째 입력 줄은 테스트 케이스의 수 T를 알려줍니다. 각각의 T 테스트 케이스는 행(라인)이 분할되어 주어지며, 각 테스트 케이스는 하나의 양의 정수 n을 알려줍니다.

제한

1 <= T <= 100
2 <= n <= 2000000000
[출력]
각 입력 테스트 케이스에 대해 다음과 같은 출력이 필요합니다.

Case #X: Y
여기서 X는 테스트 케이스 번호이고, Y는 숫자 (3 + (5)^(0.5))^n 의 마지막 세 정수입니다. 
만일 소수점 앞의 숫자(정수)가 세 자리보다 작은 경우 출력에 정확히 세 자리가 포함되도록 앞에 0을 추가하십시오.

[풀이]
A(n) = pow(3 + sqrt(5), n), B(n) = pow(3 - sqrt(5), n)이라 하자.
X(n) = A(n) + B(n)을 정의하자. (X(n)은 이항정리에 의해 항상 정수이다.)

A(n) = a(n) + b(n) * sqrt(5)로 표현할 수 있다. (a(n), b(n) 역시 이항정리에 의해 정수이다.)
A(n + 1) = A(n) * A(1) = (a(n) + b(n) * sqrt(5)) * (3 + sqrt(5))
         = (3 * a(n) + 5 * b(n)) + (a(n) + 3 * b(n)) * sqrt(5)
       
따라서 a(n + 1) = 3 * a(n) + 5 * b(n), b(n + 1) = a(n) + 3 * b(n)이다.
이는 행렬식 A = {{3, 5}, {1, 3}}으로 표현할 수 있으며, a(0) = 1, b(0) = 0이다.
{a(n + 1), b(n + 1)} = matrixPow(A, n) * {1, 0}이다.

한편, B(n) = pow(3 - sqrt(5), n)은 0에 한없이 가까워 지는 수이므로 X(n) = 2 * a(n)에 근접하다.
X(n)을 구할 때, 작은 값의 관여로 정수를 만드므로 구하는 값에 1을 빼주어야 한다. (음수가 나올 수 있으므로 999를 더하고 1000의 나머지로 구한다).

 + 참고 : https://code.google.com/codejam/contest/dashboard?c=32016#s=a&a=2
 
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