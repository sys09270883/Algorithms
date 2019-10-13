/*
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWuSgKpqmooDFASy&categoryId=AWuSgKpqmooDFASy&categoryType=CODE
[문제]

부먹 왕국은 찍먹 무리에게 침략을 당했었다.

하지만 탕수육의 힘으로 성공적으로 막아 내었으나 도시의 많은 차원관문이 파괴당했다.

부먹 왕국의 특징은 모든 도시들을 건설 할 때 일렬로 이어지게 만들었다.

어느 도시에 차원 관문을 설치하면 그 도시와 거리 D 이하인 다른 도시에서 차원 관문이 있는 곳으로 들어오거나, 혹은 차원 관문에서 거리 D 이하인 다른 도시로 나가는것이 가능하다.

찍먹 왕국의 재침공에 대비하기 위해서 모든 도시 이동이 되어야하며 모든 차원 관문 사이와 직접적으로 이동이 가능하도록 차원 관문을 재건하려고 한다.
(단, 0번 위치와 N+1번 위치에는 차원 관문이 존재 한다.)

가능한 빠른 건설을 위하여 최소 개수로 설치하는 계획을 세우려고 할때

도시들마다 차원관문이 남아있는 지에 대한 정보가 주어졌을 때, 이동에 필요한 차원관문의 최소 개수를 구하여라.


[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 부먹 왕국의 도시 수 N(1 ≤ N ≤ 300,000)과 이동 제한 거리 D(1 ≤ D ≤ N)이 주어진다.

두 번째 줄에는 1번 도시부터 차례대로 각 도시에 차원관문이 남아있는 지에 대한 정보가 주어진다.

1은 남아있음을 의미하며 0은 파괴 당한 것을 의미한다.

i번 도시와 (i+1)번 도시 사이의 거리는 모든 1 ≤ i < N에 대해서 1이다.


[출력]

각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,

각 테스트 케이스마다 부먹 왕국이 추가로 건설 해야 하는 차원관문 의 최소 개수를 구하여라.

[풀이]
0일 경우에 카운트를 올려주고 0이 아닐 때마다 그 동안의 카운트를 이동거리로 나눠주고 카운트를 0으로 초기화 시킨다. (소수는 버림)
입력에 0만 있을 경우에는 카운트를 이동거리로 나눠준 값을 출력한다.

*/
import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()), N, D, ans, cnt;
		StringBuilder res = new StringBuilder();
		StringTokenizer st = null;
		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			ans = 0;
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) != 0) {
					ans += cnt / D;
					cnt = 0;
				}
				
				else
					cnt++;
			}
			
			res.append('#').append(i).append(' ').append(ans == 0 ? ans += cnt / D : ans).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
