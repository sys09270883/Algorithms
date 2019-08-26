/*
https://www.acmicpc.net/problem/1753
[문제]
방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
단, 모든 간선의 가중치는 10 이하의 자연수이다.

[입력]
첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. 
(1≤V≤20,000, 1≤E≤300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 
둘째 줄에는 시작 정점의 번호 K(1≤K≤V)가 주어진다. 
셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 
서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

[출력]
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

[풀이]
우선순위 큐로 다익스트라 알고리즘을 구현한다.
방문 체크를 pq에서 꺼내면서 확인한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int V, E, K;
	static ArrayList<ArrayList<Vertex>> list;
	static int[] dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		list = new ArrayList<ArrayList<Vertex>>(V + 1);
		for (int i = 0; i < V + 1; i++) {
			list.add(new ArrayList<Vertex>());
		}
		dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		visited = new boolean[V + 1];
		dist[K] = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.get(u).add(new Vertex(v, w));
		}
		
		dijkstra();
		
		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] < Integer.MAX_VALUE ? dist[i] : "INF").append('\n');
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void dijkstra() {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>((o1, o2) -> o1.w - o2.w);
		pq.add(new Vertex(K, dist[K]));
		
		while(!pq.isEmpty()) {
			Vertex v1 = pq.poll();
			int cur = v1.v;
			
			if (visited[cur])
				continue;
			visited[cur] = true;
			
			for (int i = 0; i < list.get(cur).size(); i++) {
				Vertex v2 = list.get(cur).get(i);
				int next = v2.v;
				int nextW = v2.w;
				
				if (dist[next] > dist[cur] + nextW) {
					dist[next] = dist[cur] + nextW;
					pq.add(new Vertex(next, dist[next]));
				}
			}
		}
	}
}

class Vertex {
	int v;
	int w;
	
	Vertex(int v, int w) {
		this.v = v;
		this.w = w;
	}
}
