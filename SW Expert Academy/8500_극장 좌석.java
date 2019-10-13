/*
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWz5yIfq74QDFARQ
[문제]

좌석이 일렬로 나열된 극장에 N명의 사람이 앉아있다.

i번 사람의 왼쪽과 오른쪽으로 적어도 Ai개의 좌석이 연속해서 비어 있다.

즉, i번 사람은 2Ai개의 좌석이 비어 있는 것을 아는 것이다.

이 때, 극장의 좌석 개수로 가능한 수의 최소값을 구하는 프로그램을 작성하라.

사람들은 번호 순서대로 극장에 앉아 있는 것이 아님에 유의하라.


[입력]

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N(1 ≤ N ≤ 104)이 주어진다.

두 번째 줄에는 N개의 정수 A1, A2, ⋯, AN(0 ≤ Ai ≤ 104)이 공백 하나로 구분되어 주어진다.


[출력]

각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,

각 테스트 케이스마다 극장의 좌석 개수로 가능한 수의 최소값을 출력한다.

[풀이]
각 테스트 케이스마다 들어오는 값들을 리스트에 넣고 오름차순으로 정렬한다.
각 값들의 좌측에는 무조건 해당 인덱스의 값이 있어야 하며, 마지막 인덱스에는 해당 인덱스만큼의 값을 추가적으로 더해준다.

*/
import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()), N, ans;
		StringBuilder res = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<Integer>();
		StringTokenizer st = null;
		
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			list.clear();
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(list);
			
			for (int j = 0; j < N; j++) {
				ans += list.get(j);
				ans += 1;
			}
			ans += list.get(N - 1);
			
			res.append('#').append(i).append(' ').append(ans).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
