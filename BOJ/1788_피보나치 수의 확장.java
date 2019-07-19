/*
https://www.acmicpc.net/problem/1788

[문제]
수학에서, 피보나치 수는 위의 점화식과 같이 귀납적으로 정의되는 수열이다. 위의 식에서도 알 수 있듯이, 피보나치 수 F(n)은 0 이상의 n에 대해서만 정의된다.

하지만 피보나치 수 F(n)을 n이 음수인 경우로도 확장시킬 수 있다. 위의 식에서 n>1인 경우에만 성립하는 F(n)=F(n-1)+F(n-2)를 n<=1일 때도 성립되도록 정의하는 것이다. 예를 들어 n=1일 때 F(1)=F(0)+F(-1)이 성립되어야 하므로, F(-1)은 1이 되어야 한다.

n이 주어졌을 때, 피보나치 수 F(n)을 구하는 프로그램을 작성하시오. n은 음수로 주어질 수도 있다.

[입력]
첫째 줄에 n이 주어진다. n은 절댓값이 1,000,000을 넘지 않는 정수이다.

[출력]
첫째 줄에 F(n)이 양수이면 1, 0이면 0, 음수이면 -1을 출력한다. 둘째 줄에는 F(n)의 절댓값을 출력한다. 이 수가 충분히 커질 수 있으므로, 절댓값을 1,000,000,000으로 나눈 나머지를 출력한다.

[풀이]
n<0 구간에서 홀수일 경우에는 fibo[-n]이고 짝수일 경우에는 -fibo[-n]이다. 오버플로우를 유의해야 한다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] fibo = new long[1000001];
		fibo[0] = 0;
		fibo[1] = 1;
		
		long mod = 1000000000;
		for (int i = 2; i < fibo.length; i++) {
			fibo[i] = (fibo[i-2] + fibo[i-1]) % mod;
		}
		
		if(n >= 0){
			if(n == 0){
				System.out.print("0\n" + fibo[n]);
				return;
			}
			System.out.print("1\n" + fibo[n]);
		}
		else {
			if(-n % 2 == 1){
				System.out.print("1\n" + fibo[-n]);
			}
			else{ 
				System.out.print("-1\n" + fibo[-n]);
			}
		}
		
		br.close();
	}
}
