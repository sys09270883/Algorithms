/*
https://www.acmicpc.net/problem/16401
[문제]
명절이 되면, 홍익이 집에는 조카들이 놀러 온다.  떼를 쓰는 조카들을 달래기 위해 홍익이는 막대 과자를 하나씩 나눠준다.

조카들이 과자를 먹는 동안은 떼를 쓰지 않기 때문에, 홍익이는 조카들에게 최대한 긴 과자를 나눠주려고 한다.

그런데 나눠준 과자의 길이가 하나라도 다르면 조카끼리 싸움이 일어난다. 따라서 반드시 모든 조카에게 같은 길이의 막대 과자를 나눠주어야 한다.

M명의 조카가 있고 N개의 과자가 있을 때, 조카 1명에게 줄 수 있는 막대 과자의 최대 길이를 구하라.

단, 막대 과자는 길이와 상관없이 여러 조각으로 나눠질 수 있지만, 과자를 하나로 합칠 수는 없다. 단, 막대 과자의 길이는 양의 정수여야 한다.

[입력]
첫째 줄에  조카의 수 M (1 ≤ M ≤ 1,000,000), 과자의 수 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에 과자 N개의 길이 L1, L2, ..., LN이 공백으로 구분되어 주어진다. 과자의 길이는 (1 ≤ L1, L2, ..., LN ≤ 1,000,000,000) 를 만족한다.

[출력]
첫째 줄에 조카 1명에게 줄 수 있는 막대 과자의 최대 길이를 출력한다.

단, 모든 조카에게 같은 길이의 막대과자를 나눠줄 수 없다면, 0을 출력한다.

[풀이]
이분탐색으로 나누어 줄 수 있는 값 중 가장 큰 값을 구한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int[] snack;
	static int M, N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		snack = new int[1000001];
		int low = 1, high = 0, res = 0;
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, snack[i]);
		}
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			if (possible(mid)) {
				res = mid;
				low = mid + 1;
			}
			
			else
				high = mid - 1;
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static boolean possible(int len) {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			cnt += snack[i] / len;
		}
		
		if (cnt >= M)
			return true;
		
		return false;
	}
	
}
