/*
https://www.acmicpc.net/problem/15650
[문제]
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
고른 수열은 오름차순이어야 한다.
[입력]
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

[출력]
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

[풀이]
DFS를 할 때 idx부터 시작한다(작은 수에 대해서는 하지 않음).
*/
import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<Integer> list;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Integer>(8);
		visited = new boolean[N + 1];
		sb = new StringBuilder();

		DFS(1, 0);

		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static void DFS(int idx, int cnt) {
		if (cnt == M) {
			for (int i : list) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = idx; i <= N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			list.add(i);
			DFS(i, cnt + 1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
