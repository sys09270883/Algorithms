/*
https://www.acmicpc.net/problem/1504
[문제]
방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다.
 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 
1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

[입력]
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 
둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. 
(1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호가 주어진다.

[출력]
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

[풀이]
특정한 두 점 T1, T2를 거쳐서 가는 방법은
  1 -> ... -> T1 -> ... -> T2 ... -> N
  1 -> ... -> T2 -> ... -> T1 ... -> N
2가지 방법밖에 없다.

따라서 각각의 방법에 대해 3번의 다익스트라를 실행하고 두 경우 모두 길이 없는 경우를 제외하고 최솟값을 출력한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	final static int INF = Integer.MAX_VALUE;
	static int N, E;
	static ArrayList<ArrayList<Node>> adj;
	static long[] dists;
	static int T1, T2;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Node>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
		dists = new long[N + 1];
		long ans1 = 0, ans2 = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(new Node(b, c));
			adj.get(b).add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		T1 = Integer.parseInt(st.nextToken());
		T2 = Integer.parseInt(st.nextToken());
		
		ans1 = dijkstra(1, T1) + dijkstra(T1, T2) + dijkstra(T2, N);
		ans2 = dijkstra(1, T2) + dijkstra(T2, T1) + dijkstra(T1, N);
		
		if (ans1 >= INF && ans2 >= INF)
			bw.write("-1");
		
		else
			bw.write(String.valueOf(Math.min(ans1, ans2)));
		
		bw.close();
		br.close();
	}
	
	private static long dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
		Arrays.fill(dists, INF);
		dists[start] = 0;
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int cur = tmp.idx;
			long curDist = tmp.dist;
			
			if (curDist > dists[cur])
				continue;
			
			if (cur == end)
				return dists[cur];
					
			for (Node n : adj.get(cur)) {
				int next = n.idx;
				long nextDist = n.dist;
				
				if (dists[next] > dists[cur] + nextDist) {
					dists[next] = dists[cur] + nextDist;
					pq.add(new Node(next, dists[next]));
				}
			}
			
		}
		
		return INF;
	}
	
}

class Node implements Comparable<Node>{
	int idx;
	long dist;

	public Node(int idx, long dist) {
		super();
		this.idx = idx;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Long.compare(this.dist, o.dist);
	}
	
}
