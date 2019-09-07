/*
https://www.acmicpc.net/problem/1238
[����]
N���� ���ڷ� ���е� ������ ������ �� ���� �л��� ��� �ִ�.

��� �� �� N���� �л��� X (1 �� X �� N)�� ������ �𿩼� ��Ƽ�� ���̱�� �ߴ�. 
�� ���� ���̿��� �� M���� �ܹ��� ���ε��� �ְ� i��° ���� �����µ� Ti(1 �� Ti �� 100)�� �ð��� �Һ��Ѵ�.

������ �л����� ��Ƽ�� �����ϱ� ���� �ɾ�� �ٽ� �׵��� ������ ���ƿ;� �Ѵ�. ������ �� �л����� ���� �������� �ִ� �ð��� ���� ���⸦ ���Ѵ�.

�� ���ε��� �ܹ����̱� ������ �Ƹ� �׵��� ���� ���� ���� �ٸ����� �𸥴�. N���� �л��� �� ���� ���µ� ���� ���� �ð��� �Һ��ϴ� �л��� �������� ���Ͽ���.

[�Է�]
ù° �ٿ� N(1 <= N <= 1,000), M(1 <= M <= 10,000), X�� �������� ���еǾ� �Էµȴ�. 
�� ��° �ٺ��� M+1��° �ٱ��� i��° ������ ������, ����, �׸��� �� ���θ� �����µ� �ʿ��� �ҿ�ð� Ti�� ���´�.

��� �л����� ������ X�� ���� �ְ�, X���� ������ ���ƿ� �� �ִ� �����͸� �Է����� �־�����.

[���]
ù ��° �ٿ� N���� �л��� �� ���� ���µ� ���� ���� �ɸ��� �л��� �ҿ�ð��� ����Ѵ�.

[Ǯ��]
�ܹ��� �׷����̹Ƿ� ������ ��������Ʈ�� ������ ��������Ʈ �������� ���ͽ�Ʈ��� res �迭�� �����Ѵ�.
res �迭���� ���� ū ���� ����Ѵ�.

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