/*
https://www.acmicpc.net/problem/1325
[문제]
해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다. 이 회사는 N개의 컴퓨터로 이루어져 있다. 
김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.

이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데, A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.

이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에, N과 M이 들어온다. N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다. 
둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다. 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.

[출력]
첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.

[풀이]
BFS를 하면서 res 배열에 기록한다.
res의 최대값과 같은 인덱스를 출력한다
 + 싸이클이 생기는 경우에 대해서 예외처리해야한다...

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[] res;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		res = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			addEdge(A, B);
		}
		
		for (int i = 1; i < N + 1; i++) {
			BFS(i);
			Arrays.fill(visited, false);
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 1; i < N + 1; i++) {
			if (max < res[i])
				max = res[i];
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (max == res[i])
				sb.append(i).append(' ');
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void addEdge(int A, int B) {
		adj.get(A).add(B);
	}
	
	private static void BFS(int n) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		visited[n] = true;
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(i);
					res[i]++;
				}
			}
		}
	}
}
