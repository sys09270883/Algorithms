/*
https://www.acmicpc.net/problem/1238
[문제]
N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.

어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 
이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.

각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.

이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

[입력]
첫째 줄에 N(1 <= N <= 1,000), M(1 <= M <= 10,000), X가 공백으로 구분되어 입력된다. 
두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다.

모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.

[출력]
첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

[풀이]
단방향 그래프이므로 정방향 인접리스트와 역방향 인접리스트 각각에서 다익스트라로 res 배열을 갱신한다.
res 배열에서 가장 큰 값을 출력한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, X;
	static ArrayList<ArrayList<Node>> adj;
	static ArrayList<ArrayList<Node>> adj2;
	static int[] res;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Node>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
		adj2 = new ArrayList<ArrayList<Node>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj2.add(new ArrayList<Node>());
		}
		res = new int[N + 1];
		int a, b, c;
		
		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			addEdge(a, b, c);
		}
		
		dijkstra(X, adj);
		dijkstra(X, adj2);
		int ans = 0;
		for (int i = 1; i < N + 1; i++) {
			ans = Math.max(ans, res[i]);
		}
		
		bw.write(String.valueOf(ans));
		bw.close();
		br.close();
	}
	
	private static void addEdge(int a, int b, int c) {
		adj.get(a).add(new Node(b, c));
		adj2.get(b).add(new Node(a, c));
	}
	
	private static void dijkstra(int X, ArrayList<ArrayList<Node>> adj) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new NodeComparator());
		int[] cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[X] = 0;
		pq.add(new Node(X, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int v = cur.v;
			int c = cur.cost;
			
			if (c > cost[v])
				continue;
			
			for (Node n : adj.get(v)) {
				int nv = n.v;
				int nc = n.cost;
				if (cost[nv] > cost[v] + nc) {
					cost[nv] = cost[v] + nc;
					pq.add(new Node(nv, cost[nv]));
				}
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			res[i] += cost[i];
		}
	}
}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return o1.cost - o2.cost;
	}
	
}

class Node {
	int v, cost;
	
	public Node(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}
	
}