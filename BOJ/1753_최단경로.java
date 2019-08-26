/*
https://www.acmicpc.net/problem/1753
[����]
����׷����� �־����� �־��� ���������� �ٸ� ��� ���������� �ִ� ��θ� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
��, ��� ������ ����ġ�� 10 ������ �ڿ����̴�.

[�Է�]
ù° �ٿ� ������ ���� V�� ������ ���� E�� �־�����. 
(1��V��20,000, 1��E��300,000) ��� �������� 1���� V���� ��ȣ�� �Ű��� �ִٰ� �����Ѵ�. 
��° �ٿ��� ���� ������ ��ȣ K(1��K��V)�� �־�����. 
��° �ٺ��� E���� �ٿ� ���� �� ������ ��Ÿ���� �� ���� ���� (u, v, w)�� ������� �־�����. 
�̴� u���� v�� ���� ����ġ w�� ������ �����Ѵٴ� ���̴�. u�� v�� ���� �ٸ��� w�� 10 ������ �ڿ����̴�. 
���� �ٸ� �� ���� ���̿� ���� ���� ������ ������ ���� ������ �����Ѵ�.

[���]
ù° �ٺ��� V���� �ٿ� ����, i��° �ٿ� i�� ���������� �ִ� ����� ��ΰ��� ����Ѵ�. 
������ �ڽ��� 0���� ����ϰ�, ��ΰ� �������� �ʴ� ��쿡�� INF�� ����ϸ� �ȴ�.

[Ǯ��]
�켱���� ť�� ���ͽ�Ʈ�� �˰����� �����Ѵ�.
�湮 üũ�� pq���� �����鼭 Ȯ���Ѵ�.

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
