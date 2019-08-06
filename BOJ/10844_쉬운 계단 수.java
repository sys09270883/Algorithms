/*
https://www.acmicpc.net/problem/10844
[문제]
45656이란 수를 보자.

이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.

세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.

N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)

[입력]
첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

[풀이]
N은 N-1일 때 수에서 -1, +1을 해준다. (0, 9 예외처리 해야함)

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, divisor = 1000000000;
	static long[][] arr;
	static BufferedWriter bw;
	static BufferedReader br;

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new long[N+1][10];
		for (int i = 1; i < 10; i++) {
			arr[1][i] = 1;
		}
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				if(j == 0)
					arr[i][j] = arr[i-1][j+1];
				
				else if(j == 9)
					arr[i][j] = arr[i-1][j-1];
				
				else
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j+1];
				
				arr[i][j] %= divisor;
			}
		}
		
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += arr[N][i];
		}

		bw.write(String.valueOf(sum % divisor));
		bw.close();
		br.close();
	}
}

