/*
https://www.acmicpc.net/problem/14919
[문제]
0과 1사이의 실수 a1, a2, ..., aN이 입력으로 주어졌다고 하자.  구간 [0,1)을 다음과 같이 m개의 길이 L=1/m인 부분구간으로 나누자. 
각 구간은 순서대로 b1, b2, ..., bm이다.

b1: 0 ≤ x < L,
b2: L ≤ x < 2L,
...
bm: (m-1)L ≤ x < 1.
입력된 실수중, 각 구간 b1, b2, ..., bm에 포함되는 수의 개수를 출력하시오.

[입력]
첫줄에 m (1 ≤ m ≤ 1,000), 둘째 줄에 N (1 ≤ N ≤ 1,000,000)개의 실수 a1, …, aN이 빈칸으로 구분되어 입력된다. 실수는 소수점 여섯째 자리까지 주어진다.

[출력]
각 구간 b1, b2, ..., bm에 포함된 수를 빈 칸으로 구분해 출력한다.

[풀이]
문제의 알고리즘 자체는 쉬웠는데 부동소수점 처리가 까다로웠던 문제였다.
double형의 경우 0.3이 사실은 0.30000000000으로 들어 가는 것이 아니라 약간의 오차가 있게 들어가기 때문에 이것을 해결해야 했다.
구간을 최대 1000개까지 나눌 수 있기 때문에 소수점 6자리 + 3자리(1000)까지는 정확히 표현해야 한다.

따라서 10번째 자리에 1을 더해주고 100만을 곱해주면 구하려는 위치까지는 구할 수 있다.

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String... args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int m, MUL = 1000000;
		double DIV = 0.0000000001;
		m = Integer.parseInt(br.readLine());
		int[] arr = new int[m];
		int[] res = new int[m];
		double MOD = (double)1 / m + DIV;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < m; i++) {
			arr[i] = (int)((i + 1) * MOD * MUL);
			if (((i + 1) * MUL) % m != 0)
				arr[i]++;
		}
		
		while (st.hasMoreTokens()) {
			double x = Double.parseDouble(st.nextToken());
			x += DIV;
			int real = (int)(x * MUL);
			
			for (int i = 0; i < m; i++) {
				if (real < arr[i]) {
					res[i]++;
					break;
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			sb.append(res[i]).append(' ');
		}
			
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}