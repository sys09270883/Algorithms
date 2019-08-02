/*
https://www.acmicpc.net/problem/1260
[문제]
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

[입력]
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

[출력]
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

[풀이]
DFS
  특징
    1) 자기 자신을 호출하는 순환 알고리즘의 형태를 가지고 있다.(스택)
    2) 트리 순회는 모두 DFS의 한 종류이다.
    3) 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야 한다.(visited)
  구현
    1) root 노드 방문
    2) root 노드와 인접한 정점을 모두 방문(adjacentList)
    3) 방문하지 않은 정점을 찾음
    4) root 노드와 인접한 정점을 시작으로 BFS 재귀
    
BFS
  특징
    1) 직관적이지 않다.
    2) 재귀적으로 동작하지 않는다.
    3) 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야 한다.(visited)
    4) BFS는 방문한 노드들을 차례로 저장한 후 꺼낼 수 있게 큐를 사용한다.
  구현
    1) root노드를 방문한 노드로 체크, 큐의 끝에 추가
    2) 큐의 앞에서 노드 추출
    3) 큐에서 추출한 노드 방문
    4) 추출한 노드와 인접 노드들을 모두 차례로 방문
    5) 방문한 노드로 체크, 큐의 끝에 추가
    6) 큐가 빌 때까지 계속 반복

+ 인접 리스트 시간복잡도 : O(N+E)
+ 인접 행렬 시간복잡도 : O(N^2)
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int MAX = 1001;
	static ArrayList<ArrayList<Integer>> adjList;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M, V;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		adjList = new ArrayList<ArrayList<Integer>>(MAX);
		for (int i = 0; i < N+1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		visited = new boolean[MAX];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		for (int i = 1; i <= N; i++) 
			Collections.sort(adjList.get(i));
		
		DFS(V);
		Arrays.fill(visited, false);
		sb.append("\n");
		BFS(V);
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void DFS(int idx) {
		visited[idx] = true;
		sb.append(idx).append(" ");
		
		for (int i : adjList.get(idx)) {
			if(!visited[i])
				DFS(i);
		}
	}
	
	private static void BFS(int idx) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[idx] = true;
		queue.add(idx);
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			sb.append(tmp).append(" ");
			
			for(int i : adjList.get(tmp)) {
				if(!visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
