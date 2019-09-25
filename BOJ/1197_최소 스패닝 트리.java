/*
https://www.acmicpc.net/problem/1197
[문제]
그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

[입력]
첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

최소 스패닝 트리의 가중치가 -2147483648보다 크거나 같고, 2147483647보다 작거나 같은 데이터만 입력으로 주어진다.

[출력]
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

[풀이]
크루스칼 알고리즘으로 최소 스패닝 트리를 구한다.
간선들을 오름차순으로 정렬한 후, 노드들이 서로 연결되어있지 않다면(findSameParent) 서로 연결한다.(union-find)
그리고 그 간선에 있는 코스트를 더해준다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static int MAX_SIZE = 10001;
	static int V, E;
	static ArrayList<Edge> edge;
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edge = new ArrayList<Edge>();
		parent = new int[MAX_SIZE];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A, B, C;
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			edge.add(new Edge(A, B, C));
		}
		
		Collections.sort(edge, (o1, o2) -> o1.cost - o2.cost);
		
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			int from = edge.get(i).from;
			int to = edge.get(i).to;
			int cost = edge.get(i).cost;
			
			if (!isSameParent(from, to)) {
				union(from, to);
				res += cost; 
			}
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (parent[x] == x)
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y)
			parent[y] = x;
	}
	
	private static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y)
			return true;
		
		return false;
	}
}

class Edge {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
}