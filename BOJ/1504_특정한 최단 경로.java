/*
https://www.acmicpc.net/problem/1504
[����]
���⼺�� ���� �׷����� �־�����. �����̴� 1�� �������� N�� �������� �ִ� �Ÿ��� �̵��Ϸ��� �Ѵ�.
 ���� �����̴� �� ���� ������ �����ϸ鼭 �̵��ϴ� Ư���� �ִ� ��θ� ���ϰ� ������, �װ��� �ٷ� ���Ƿ� �־��� �� ������ �ݵ�� ����ؾ� �Ѵٴ� ���̴�.

�����̴� �ѹ� �̵��ߴ� ������ ����, �ѹ� �̵��ߴ� ������ �ٽ� �̵��� �� �ִ�. ������ �ݵ�� �ִ� ��η� �̵��ؾ� �Ѵٴ� ��ǿ� �����϶�. 
1�� �������� N�� �������� �̵��� ��, �־��� �� ������ �ݵ�� ��ġ�鼭 �ִ� ��η� �̵��ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� N�� ������ ���� E�� �־�����. (2 �� N �� 800, 0 �� E �� 200,000) 
��° �ٺ��� E���� �ٿ� ���ļ� �� ���� ���� a, b, c�� �־����µ�, a�� �������� b�� �������� ����� ���� �����ϸ�, �� �Ÿ��� c��� ���̴�. 
(1 �� c �� 1,000) ���� �ٿ��� �ݵ�� ���ľ� �ϴ� �� ���� ���� �ٸ� ���� ��ȣ�� �־�����.

[���]
ù° �ٿ� �� ���� ������ ������ �ִ� ����� ���̸� ����Ѵ�. �׷��� ��ΰ� ���� ������ -1�� ����Ѵ�.

[Ǯ��]
Ư���� �� �� T1, T2�� ���ļ� ���� �����
  1 -> ... -> T1 -> ... -> T2 ... -> N
  1 -> ... -> T2 -> ... -> T1 ... -> N
2���� ����ۿ� ����.

���� ������ ����� ���� 3���� ���ͽ�Ʈ�� �����ϰ� �� ��� ��� ���� ���� ��츦 �����ϰ� �ּڰ��� ����Ѵ�.

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
