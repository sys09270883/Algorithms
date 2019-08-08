/*
https://www.acmicpc.net/problem/1711
[문제]
2차원 평면에 N개의 점이 주어져 있다. 이 중에서 세 점을 골랐을 때, 직각삼각형이 몇 개나 있는지를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 점의 개수 N(3≤N≤1,500)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 점의 x좌표와 y좌표가 빈 칸을 사이에 두고 주어진다. 좌표값은 절댓값이 1,000,000,000을 넘지 않는 정수이며, 주어지는 모든 점의 좌표는 다르다고 가정해도 좋다.

[출력]
첫째 줄에 직각삼각형의 개수를 출력한다.

[풀이]
세 점을 선택해서 직각인 각이 있으면 카운팅을 해 주었다.
벡터의 내적으로 직각을 판단했고, 내적 과정에서 int 범위를 넘을 수 있기 때문에 long으로 선언해야 한다.

+ c^2 = a^2 + b^2으로 직각을 판단하는 것은 연산이 너무 많이 일어나서 TLE였다.
+ 스위핑 공부해보기

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long[][] arr;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		arr = new long[N + 1][2];
		cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				for (int k = j + 1; k < N + 1; k++) {
					isRightAngle(i, j, k);
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
	
	private static void isRightAngle(int i, int j, int k){
		if(isZero(i, j, k) || isZero(j, k, i) || isZero(k, i, j))
			cnt++;
	}
	
	private static boolean isZero(int i, int j, int k) {
		if((arr[i][0] - arr[j][0]) * (arr[i][0] - arr[k][0]) + (arr[i][1] - arr[j][1]) * (arr[i][1] - arr[k][1]) == 0)
			return true;
		
		return false;
	}
}
