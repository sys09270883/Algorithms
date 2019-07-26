/*
https://www.acmicpc.net/problem/2086
[문제]
제 1항과 제 2항을 1이라 하고, 제 3항부터는 앞의 두 항의 합을 취하는 수열을 피보나치(fibonacci) 수열이라고 한다. 예를 들어 제 3항은 2이며, 제 4항은 3이다.

피보나치 수열의 a번째 항부터 b번째 항까지의 합을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으므로 마지막 아홉 자리만을 구하도록 한다. 즉 1,000,000,000으로 나눈 나머지를 구하면 된다.

[입력]
첫째 줄에 a와 b(1≤a≤b≤9,000,000,000,000,000,000)이 주어진다.

[출력]
첫째 줄에 구한 합을 출력한다.

[풀이]
피보나치 수열의 n번째 항까지의 합은 n+2번째 항 -1이다. (점화식으로 증명)
b+2번째 항과 (a-1)+2번째 항의 차이를 출력한다.
출력할 때, 음수가 나올 경우를 예외처리해야한다.

+ final static int mod = 1000000000;	-> 이 코드에서 0을 하나 빼먹어서 헤맸다... 조건을 잘 확인하자!
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	final static int mod = 1000000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long[][] fibo = {{1, 1}, {1, 0}};
		
		long[][] matrixB = matrixPow(fibo, b+2);
		long[][] matrixA = matrixPow(fibo, a+1);
		
		System.out.println(((matrixB[0][1] - 1) % mod - (matrixA[0][1] - 1) % mod + mod) % mod);
		
		bw.close();
		br.close();	
	}

	private static long[][] matrixMul(long[][] a, long[][] b){
		long c[][] = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] += (a[i][k] % mod * b[k][j] % mod) % mod;
				}
				c[i][j] %= mod;
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
